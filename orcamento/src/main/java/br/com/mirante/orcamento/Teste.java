package br.com.mirante.orcamento;

import java.sql.SQLException;

import javax.persistence.Entity;
//import br.com.mirante.projetoOrcamento.domain.Orcamento;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Persistence;

@Entity
class Aluno{
	@Id
	String nome;
	public String getNome() {
		return nome;
	}
}

public class Teste {
	
	public static void main(String[] args) throws SQLException {
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("default");
		EntityManager entityManager = fabrica.createEntityManager();
		//entityManager.persist(new Orcamento());
	}

}
