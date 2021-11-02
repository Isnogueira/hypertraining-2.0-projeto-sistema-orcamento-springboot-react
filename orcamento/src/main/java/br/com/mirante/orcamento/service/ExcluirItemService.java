package br.com.mirante.orcamento.service;

import br.com.mirante.orcamento.repository.ItemRepository;
//import br.com.mirante.orcamento.repository.ItemRepositoryJpa;

public class ExcluirItemService {
	
	public ExcluirItemService(ItemRepository repository ) {
		
		this.repository = repository;
	}

	private ItemRepository repository;

	public void excluirItem(Integer idItem) {

		repository.excluir(idItem);
	}
}
