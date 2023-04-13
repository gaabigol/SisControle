package com.SisControle.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.SisControle.model.ProdutoEstoque;

@Repository
@Transactional
public class ProdutoEstoqueDAO implements CRUD<ProdutoEstoque, Long> {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public ProdutoEstoque findById(Long id) {
		return entityManager.find(ProdutoEstoque.class, id);
	}

	@Override
	public List<ProdutoEstoque> findAll() {
		TypedQuery<ProdutoEstoque> query = entityManager.createQuery("SELECT p FROM ProdutoEstoque p",
				ProdutoEstoque.class);
		List<ProdutoEstoque> produtos = query.getResultList();
		return produtos;
	}

	@Override
	public void insert(ProdutoEstoque produto) {
		entityManager.persist(produto);
	}

	@Override
	public void remove(ProdutoEstoque produto) {
		if (entityManager.contains(produto)) {
			entityManager.remove(produto);
		}

	}

	@Override
	public void update(ProdutoEstoque produto) {
		if (entityManager.contains(produto)) {
			entityManager.merge(produto);
		}
	}

}
