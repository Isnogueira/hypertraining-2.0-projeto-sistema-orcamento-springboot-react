package br.com.mirante.orcamento.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.mirante.orcamento.domain.Orcamento;

@Repository
public interface OrcamentoRepository {

	int obterMaiorId();

	void salvar(Orcamento orcamento);

	List<Orcamento> listar();
	
	Orcamento recuperar(int id);

	void excluir(Integer id);


}
