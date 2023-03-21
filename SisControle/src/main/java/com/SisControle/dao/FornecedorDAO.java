package com.SisControle.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.SisControle.model.Fornecedor;

@Repository
@Transactional
public class FornecedorDAO implements CRUD<Fornecedor, Long> {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Fornecedor findById(Long id) {
		return entityManager.find(Fornecedor.class, id);
	}

	@Override
	public List<Fornecedor> findAll() {
		TypedQuery<Fornecedor> query = entityManager.createNamedQuery("SELECT f FROM Fornecedor f", Fornecedor.class);
		List<Fornecedor> fornecedores = query.getResultList();
		return fornecedores;
	}

	@Override
	public void insert(Fornecedor fornecedor) {
		entityManager.persist(fornecedor);
	}

	@Override
	public void remove(Fornecedor fornecedor) {
		if (entityManager.contains(fornecedor)) {
			entityManager.remove(fornecedor);
		}
	}

	@Override
	public void update(Fornecedor fornecedor) {
		entityManager.merge(fornecedor);
	}

}
