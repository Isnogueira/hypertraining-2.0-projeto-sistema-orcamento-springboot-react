package br.com.mirante.orcamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mirante.orcamento.domain.ItemOrcamento;
import br.com.mirante.orcamento.domain.Orcamento;
import br.com.mirante.orcamento.repository.ItemOrcamentoRepositoryJpa;
import br.com.mirante.orcamento.repository.OrcamentoRepository;

@Service
public class ListarOrcamentosService {
	
	@Autowired
	private OrcamentoRepository orcamentoRepository;
	
	@Autowired
	private ItemOrcamentoRepositoryJpa itensOrcamentoRepository;
	
	
	public List<Orcamento> listarOrcamentos(){
		
		List<Orcamento> orcamentos = orcamentoRepository.listar();
		
		return orcamentos.stream().map(this::atribuirValorTotal).toList();
	
	}

	private Orcamento atribuirValorTotal(Orcamento orcamento) {
		
		orcamento.setValorTotalCalculado(calcularValorTotalOrcamento(orcamento));
		
		return orcamento;
	}

	
	private Float calcularValorTotalOrcamento(Orcamento orcamento) {
		
		List<ItemOrcamento> itens = itensOrcamentoRepository.listarPorIdOrcamento(orcamento.getId());
		Float valorTotalOrcamento = 0f;
		
		for(ItemOrcamento itemOrcamento : itens) {
			
			valorTotalOrcamento += itemOrcamento.getValorTotalCalculado();
		}
				
		return valorTotalOrcamento;
	}
	
	

}
