package com.SisControle.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.SisControle.model.Produto;

@Repository
@Transactional
public class ProdutoDAO implements CRUD<Produto, Long> {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Produto findById(Long id) {
		return entityManager.find(Produto.class, id);
	}

	@Override
	public List<Produto> findAll() {
		TypedQuery<Produto> query = entityManager.createQuery("SELECT p FROM Produto p", Produto.class);
		List<Produto> produtos = query.getResultList();
		return produtos;
	}

	@Override
	public void insert(Produto produto) {
		entityManager.persist(produto);
	}

	@Override
	public void remove(Produto produto) {
		if (entityManager.contains(produto)) {
			entityManager.remove(produto);
		}
	}

	@Override
	public void update(Produto produto) {
		entityManager.merge(produto);
	}

}
