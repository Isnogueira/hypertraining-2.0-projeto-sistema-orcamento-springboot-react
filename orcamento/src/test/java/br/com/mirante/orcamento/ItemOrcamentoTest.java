package br.com.mirante.orcamento;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.mirante.orcamento.domain.ItemOrcamento;

public class ItemOrcamentoTest {
	
	@Test
	public void deveGerarInconsistenciaSeOCalculoDivergir() {
		
		ItemOrcamento item = new ItemOrcamento("1", "IBGE", "abc", 5.2f, "un", 3.5f, 18.3f
				);
		
		Assertions.assertTrue(item.possuiInconsistencia());
	}
	
	@Test
	public void naoDeveGerarInconsistenciaSeOCalculoDivergir() {
		
		ItemOrcamento item = new ItemOrcamento("1", "IBGE", "abc", 5.2f, "un", 7.2f, 23.4f
				);
		
		Assertions.assertFalse(item.possuiInconsistencia());
	}

}
