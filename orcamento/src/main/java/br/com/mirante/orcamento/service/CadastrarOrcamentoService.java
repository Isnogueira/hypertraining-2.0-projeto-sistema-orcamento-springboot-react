package br.com.mirante.orcamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mirante.orcamento.domain.ItemOrcamento;
import br.com.mirante.orcamento.domain.Orcamento;
import br.com.mirante.orcamento.repository.ItemOrcamentoRepositoryJpa;
import br.com.mirante.orcamento.repository.OrcamentoRepositoryJpa;

@Service
public class CadastrarOrcamentoService {
	
	@Autowired
	private OrcamentoRepositoryJpa orcamentoRepository;
	
	@Autowired
	private ItemOrcamentoRepositoryJpa itemRepository;
	
	@Transactional
	public Orcamento cadastrar(Orcamento orcamento, List<ItemOrcamento> itens) {
		
		int maiorId = orcamentoRepository.obterMaiorId();
		orcamento.setId(maiorId + 1);
		orcamentoRepository.salvar(orcamento);
		itens.forEach(item -> item.setOrcamento(orcamento));
		itemRepository.saveAll(itens);
		return orcamento;
		
	}

}
