package br.com.mirante.orcamento.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mirante.orcamento.domain.ItemOrcamento;
import br.com.mirante.orcamento.domain.Orcamento;
import br.com.mirante.orcamento.domain.Produto;
import br.com.mirante.orcamento.repository.ItemOrcamentoRepositoryJpa;
import br.com.mirante.orcamento.repository.OrcamentoRepository;
import br.com.mirante.orcamento.repository.ReferenciaPrecoRepository;

@Service
public class RecuperarInconsistenciaService {

	@Autowired
	private ItemOrcamentoRepositoryJpa itensRepository;

	@Autowired
	private ReferenciaPrecoRepository referenciaPrecoRepository;

	@Autowired
	private OrcamentoRepository orcamentoRepository;

	public List<String> recuperarInconsistencias(Integer idOrcamento) {

		List<String> inconsistencias = new ArrayList<>();

		Orcamento orcamento = orcamentoRepository.recuperar(idOrcamento);

		List<ItemOrcamento> itens = itensRepository.listarPorIdOrcamento(idOrcamento);

		Map<String, Produto> produtos = referenciaPrecoRepository.recuperarProdutos(orcamento.getMes(),
				orcamento.getAno(), itens.stream().map(ItemOrcamento::getCodigo).toList());

		for (int index = 0; index < itens.size(); index++) {

			ItemOrcamento itemOrcamento = itens.get(index);
			int numeroItem = index + 1;

			inconsistenciasDoItem(inconsistencias, itemOrcamento, numeroItem, produtos);

		}

		return inconsistencias;

	}

	private void inconsistenciasDoItem(List<String> inconsistencias, ItemOrcamento itemOrcamento,
			int numeroItem, Map<String, Produto> produtos) {

		if (itemOrcamento.getQuantidade() == 0F) {
			inconsistencias.add("O item de id " + numeroItem + " possui quantidade iguala zero.");
		}

		if (itemOrcamento.getValorTotalCalculado() != itemOrcamento.getValorTotalInformado()) {

			inconsistencias.add("O valor total do item de id " + itemOrcamento.getId() + " deveria ser R$"
					+ itemOrcamento.getValorTotalCalculado() + " mas foi R$"
					+ itemOrcamento.getValorTotalInformado());
		}

		if (itemOrcamento.getValorTotalCalculado() != itemOrcamento.getValorTotalInformado()) {
			inconsistencias.add("O valor total do item de id " + numeroItem + " deveria ser R$"
					+ itemOrcamento.getValorTotalCalculado() + " mas foi R$"
					+ itemOrcamento.getValorTotalInformado());
		}

		if (produtos.containsKey(itemOrcamento.getCodigo())) {
			Produto produto = produtos.get(itemOrcamento.getCodigo());

			if (!produto.getUnidade().equals(itemOrcamento.getUnidade())) {
				inconsistencias
						.add("A unidade do item numero " + numeroItem + "(" + itemOrcamento.getUnidade() + ")"
								+ " diverge da unidade de referencia que ?? " + produto.getUnidade());
			}

			var valorTotalCalculadoReferencia = produto.getValorInformado() * itemOrcamento.getQuantidade();

			if (itemOrcamento.getValorTotalCalculado() > valorTotalCalculadoReferencia) {
				var valorSobrepreco = itemOrcamento.getValorTotalCalculado() - valorTotalCalculadoReferencia;
				var percentual = valorSobrepreco / valorTotalCalculadoReferencia * 100;

				inconsistencias
						.add("O item numero " + numeroItem + "possui sobrepreco de R$ " + percentual + "%");
			}

		} else {
			inconsistencias.add("O item " + numeroItem + " n??o possuiu uma referencia de pre??o valida");
		}

	}
}
