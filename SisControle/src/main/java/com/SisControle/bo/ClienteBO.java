package com.SisControle.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SisControle.dao.CRUD;
import com.SisControle.dao.ClienteDAO;
import com.SisControle.model.Cliente;

@Service
public class ClienteBO implements CRUD<Cliente, Long> {

	@Autowired
	private ClienteDAO dao;

	@Override
	public Cliente findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<Cliente> findAll() {
		return dao.findAll();
	}

	@Override
	public void insert(Cliente cliente) {
		dao.insert(cliente);
	}

	@Override
	public void remove(Cliente cliente) {
		dao.remove(cliente);
	}

	@Override
	public void update(Cliente cliente) {
		dao.update(cliente);
	}

	public void activate(Cliente cliente) {
		cliente.setAtivo(true);
		dao.update(cliente);
	}

	public void inactivate(Cliente cliente) {
		cliente.setAtivo(false);
		dao.update(cliente);
	}

}
