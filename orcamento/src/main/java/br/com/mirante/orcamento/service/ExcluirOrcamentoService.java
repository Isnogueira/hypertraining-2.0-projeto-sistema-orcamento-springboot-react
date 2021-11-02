package br.com.mirante.orcamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mirante.orcamento.repository.ItemRepository;
import br.com.mirante.orcamento.repository.OrcamentoRepositoryJpa;

@Service
public class ExcluirOrcamentoService {
	
	@Autowired
	private OrcamentoRepositoryJpa orcamentoRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Transactional
	public void excluir(Integer id) {
		
		itemRepository.excluirItens(id);
		orcamentoRepository.excluir(id);		
	}

}
