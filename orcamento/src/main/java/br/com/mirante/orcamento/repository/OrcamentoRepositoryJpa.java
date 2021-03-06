package br.com.mirante.orcamento.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.mirante.orcamento.domain.Orcamento;

@Repository
public class OrcamentoRepositoryJpa implements OrcamentoRepository{

	@Autowired
	private EntityManager entityManager;

	@Override
	public int obterMaiorId() {
		var maiorId = entityManager.createQuery("select max(o.id) from Orcamento o").getSingleResult();
		return maiorId == null? 0 : (Integer) maiorId;
	}

	@Override
	public void salvar(Orcamento orcamento) {
		entityManager.merge(orcamento);
	}

	@Override
	public List<Orcamento> listar() {
		return entityManager.createQuery("from Orcamento", Orcamento.class).getResultList();

	}

	@Override
	public Orcamento recuperar(int id) {
		return entityManager.find(Orcamento.class, id);
	}
	
	@Transactional
	@Override
	public void excluir(Integer id) {
		entityManager.createQuery("delete from Orcamento o where o.id = :id")
		.setParameter("id", id)
		.executeUpdate();
		
	}


}
