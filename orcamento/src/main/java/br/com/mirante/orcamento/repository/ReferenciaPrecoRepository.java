package br.com.mirante.orcamento.repository;

import java.util.List;
import java.util.Map;

import br.com.mirante.orcamento.domain.Produto;

public interface ReferenciaPrecoRepository {

	Map<String, Produto> recuperarProdutos(Integer mes, Integer ano, List<String> list);

	

}
