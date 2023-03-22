package com.SisControle.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SisControle.dao.CRUD;
import com.SisControle.dao.ProdutoDAO;
import com.SisControle.model.Produto;

@Service
public class ProdutoBO implements CRUD<Produto, Long>{

	@Autowired
	private ProdutoDAO dao;
	
	
	@Override
	public Produto findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<Produto> findAll() {
		return dao.findAll();
	}

	@Override
	public void insert(Produto produto) {
		dao.insert(produto);
	}

	@Override
	public void remove(Produto produto) {
		dao.remove(produto);
	}

	@Override
	public void update(Produto produto) {
		dao.update(produto);
	}
	
	public void activate(Produto produto) {
		produto.setAtivo(true);
		dao.update(produto);
	}
	
	public void inactivate(Produto produto) {
		produto.setAtivo(false);
		dao.update(produto);
	}

}
