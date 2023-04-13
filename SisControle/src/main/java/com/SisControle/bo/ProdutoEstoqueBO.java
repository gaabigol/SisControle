package com.SisControle.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SisControle.dao.CRUD;
import com.SisControle.dao.ProdutoEstoqueDAO;
import com.SisControle.model.ProdutoEstoque;

@Service
public class ProdutoEstoqueBO implements CRUD<ProdutoEstoque, Long> {

	@Autowired
	private ProdutoEstoqueDAO dao;

	@Override
	public ProdutoEstoque findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<ProdutoEstoque> findAll() {
		return dao.findAll();
	}

	@Override
	public void insert(ProdutoEstoque produto) {
		dao.insert(produto);
	}

	@Override
	public void remove(ProdutoEstoque produto) {
		dao.remove(produto);
	}

	@Override
	public void update(ProdutoEstoque produto) {
		dao.update(produto);
	}

}
