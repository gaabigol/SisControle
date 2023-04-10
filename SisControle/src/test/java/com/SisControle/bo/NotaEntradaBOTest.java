package com.SisControle.bo;


import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SisControle.model.Fornecedor;
import com.SisControle.model.NotaEntrada;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class NotaEntradaBOTest {

	@Autowired
	private NotaEntradaBO bo;

	@Test
	@Order(1)
	public void insert() {
		NotaEntrada nota = new NotaEntrada();
		nota.setFornecedor(new Fornecedor());
		nota.setTotal(20.45);
		bo.insert(nota);
	}

	@Test
	@Order(2)
	public void findById() {
		NotaEntrada nota = bo.findById(1L);
		System.out.println(nota);
	}

	@Test
	@Order(3)
	public void update() {
		NotaEntrada nota = bo.findById(1L);
		nota.setTotal(50.22);
		bo.update(nota);
	}

	@Test
	@Order(4)
	public void findAll() {
		List<NotaEntrada> nota = bo.findAll();
		for (NotaEntrada notas : nota) {
			System.out.println(notas);
		}

	}

	@Test
	@Order(5)
	public void remove() {
		NotaEntrada nota = bo.findById(1L);
		bo.remove(nota);
	}
}
