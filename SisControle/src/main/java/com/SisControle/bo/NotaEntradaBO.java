package com.SisControle.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SisControle.dao.CRUD;
import com.SisControle.dao.NotaEntradaDAO;
import com.SisControle.model.NotaEntrada;

@Service
public class NotaEntradaBO implements CRUD<NotaEntrada, Long> {

	@Autowired
	NotaEntradaDAO dao;

	@Override
	public NotaEntrada findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<NotaEntrada> findAll() {
		return dao.findAll();
	}

	@Override
	public void insert(NotaEntrada notaEntrada) {
		dao.insert(notaEntrada);
	}

	@Override
	public void remove(NotaEntrada notaEntrada) {
		dao.remove(notaEntrada);
	}

	@Override
	public void update(NotaEntrada notaEntrada) {
		dao.update(notaEntrada);
	}

}
