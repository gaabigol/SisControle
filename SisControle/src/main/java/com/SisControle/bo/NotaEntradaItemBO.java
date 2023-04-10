package com.SisControle.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SisControle.dao.CRUD;
import com.SisControle.dao.NotaEntradaItemDAO;
import com.SisControle.model.NotaEntradaItem;

@Service
public class NotaEntradaItemBO implements CRUD<NotaEntradaItem, Long> {

	@Autowired
	private NotaEntradaItemDAO dao;
	
	
	@Override
	public NotaEntradaItem findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<NotaEntradaItem> findAll() {
		return dao.findAll();
	}

	@Override
	public void insert(NotaEntradaItem item) {
		dao.insert(item);
	}

	@Override
	public void remove(NotaEntradaItem item) {
		dao.remove(item);
	}

	@Override
	public void update(NotaEntradaItem item) {
		dao.update(item);
	}
	

}
