package com.SisControle.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.SisControle.model.Cliente;

@Repository
@Transactional
public class ClienteDAO implements CRUD<Cliente, Long> {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Cliente findById(Long id) {
		return entityManager.find(Cliente.class, id);
	}

	@Override
	public List<Cliente> findAll() {
		TypedQuery<Cliente> query = entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class);
		List<Cliente> clientes = query.getResultList();
		return clientes;
	}

	@Override
	public void insert(Cliente cliente) {
		entityManager.persist(cliente);
	}

	@Override
	public void remove(Cliente cliente) {
		if (entityManager.contains(cliente)) {
			entityManager.remove(cliente);
		}
	}

	@Override
	public void update(Cliente cliente) {
		entityManager.merge(cliente);

	}
}
