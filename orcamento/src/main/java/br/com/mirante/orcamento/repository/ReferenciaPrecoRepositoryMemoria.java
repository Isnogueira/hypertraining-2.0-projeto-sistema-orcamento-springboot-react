package br.com.mirante.orcamento.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.com.mirante.orcamento.domain.Produto;

@Repository
public class ReferenciaPrecoRepositoryMemoria implements ReferenciaPrecoRepository{
    
	@Override
    public Map<String, Produto> recuperarProdutos(Integer mes, Integer ano, List<String> codigos) {
        
		Map<String, Produto> produtos = new HashMap<>();
        produtos.put("1234", new Produto("IBGE", "1234", "Arroz", "T", 2.0F));
        
        return produtos;
    }

}

/*
"origem":"IBGE",
            "codigo":"ABDC",
            "descricao":"Feijão",
            "valorUnitario":9.50,
            "unidade":"kg",
            "quantidade":5,
            "valorTotalInformado":70

*/