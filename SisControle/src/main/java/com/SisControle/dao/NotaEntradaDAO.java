package com.SisControle.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.SisControle.model.NotaEntrada;

@Repository
@Transactional
public class NotaEntradaDAO implements CRUD<NotaEntrada, Long> {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public NotaEntrada findById(Long id) {
		return entityManager.find(NotaEntrada.class, id);
	}

	@Override
	public List<NotaEntrada> findAll() {
		TypedQuery<NotaEntrada> query = entityManager.createNamedQuery("SELECT ne FROM NOTAENTRADA ne",
				NotaEntrada.class);
		List<NotaEntrada> notas = query.getResultList();
		return notas;
	}

	@Override
	public void insert(NotaEntrada notaEntrada) {
		entityManager.persist(notaEntrada);
	}

	@Override
	public void remove(NotaEntrada notaEntrada) {
		entityManager.remove(notaEntrada);
	}

	@Override
	public void update(NotaEntrada notaEntrada) {
		entityManager.merge(notaEntrada);
	}

}
