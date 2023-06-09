package com.SisControle.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.SisControle.model.NotaEntradaItem;

@Repository
@Transactional
public class NotaEntradaItemDAO implements CRUD<NotaEntradaItem, Long> {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public NotaEntradaItem findById(Long id) {
		return entityManager.find(NotaEntradaItem.class, id);
	}

	@Override
	public List<NotaEntradaItem> findAll() {
		Query query = entityManager.createQuery("select ne from NotaEntradaItem ne");
		return query.getResultList();
	}

	@Override
	public void insert(NotaEntradaItem notaEntradaItem) {
		entityManager.persist(notaEntradaItem);
	}

	@Override
	public void remove(NotaEntradaItem notaEntradaItem) {
		if(entityManager.contains(notaEntradaItem)) {
			entityManager.remove(notaEntradaItem);
			}
		}
		
	@Override
	public void update(NotaEntradaItem notaEntradaItem) {
		if (entityManager.contains(notaEntradaItem)) {
			entityManager.merge(notaEntradaItem);
		}
	}

	public List<NotaEntradaItem> listaItensNota(Long notaEntradaId) {
		Query query = entityManager.createQuery("from NotaEntradaItem n where n.notaEntrada.id = :notaEntradaId")
				.setParameter("notaEntradaId", notaEntradaId);
		return query.getResultList();
	}

}
