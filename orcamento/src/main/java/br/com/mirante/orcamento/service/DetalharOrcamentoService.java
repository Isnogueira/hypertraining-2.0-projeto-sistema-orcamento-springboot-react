package br.com.mirante.orcamento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mirante.orcamento.domain.ItemOrcamento;
import br.com.mirante.orcamento.domain.Orcamento;
import br.com.mirante.orcamento.repository.ItemOrcamentoRepositoryJpa;
import br.com.mirante.orcamento.repository.OrcamentoRepository;

@Service
public class DetalharOrcamentoService {
	
	@Autowired
	OrcamentoRepository orcamentoRepository;
	
	@Autowired
	ItemOrcamentoRepositoryJpa itensOrcamentoRepository;

	public Orcamento recuperar(int id) {
	
		return orcamentoRepository.recuperar(id);
	}

	public List<ItemOrcamento> recuperarItens(Integer id) {
		
		return itensOrcamentoRepository.listarPorIdOrcamento(id);
	}


}
