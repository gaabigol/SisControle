package com.SisControle.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SisControle.dao.CRUD;
import com.SisControle.dao.FornecedorDAO;
import com.SisControle.model.Fornecedor;

@Service
public class FornecedorBO implements CRUD<Fornecedor, Long> {

	@Autowired
	FornecedorDAO dao;

	@Override
	public Fornecedor findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<Fornecedor> findAll() {
		return dao.findAll();
	}

	@Override
	public void insert(Fornecedor fornecedor) {
		dao.insert(fornecedor);
	}

	@Override
	public void remove(Fornecedor fornecedor) {
		dao.remove(fornecedor);
	}

	@Override
	public void update(Fornecedor fornecedor) {
		dao.update(fornecedor);
	}

	public void activate(Fornecedor fornecedor) {
		fornecedor.setAtivo(true);
		dao.update(fornecedor);
	}

	public void inactivate(Fornecedor fornecedor) {
		fornecedor.setAtivo(false);
		dao.update(fornecedor);
	}

}
