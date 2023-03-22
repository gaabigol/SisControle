package com.SisControle.bo;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SisControle.model.Produto;
import com.SisControle.model.enums.Categoria;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ProdutoBOTest {
	
	@Autowired
	private ProdutoBO bo;

	@Test
	@Order(1)
	public void insert() {
		Produto produto = new Produto();
		produto.setCategoria(Categoria.INFORMATICA);
		produto.setNome("processador i5");
		bo.insert(produto);
	}
	
	@Test
	@Order(2)
	public void findById() {
		Produto produto = bo.findById(1L);
		System.out.println(produto);
	}
	
	@Test
	@Order(3)
	public void update() {
		Produto produto = bo.findById(1L);
		produto.setCategoria(Categoria.MOVEIS);
		bo.update(produto);
		System.out.println(produto);
	}
	
	@Test
	@Order(4)
	public void findAll() {
		List<Produto> produtos = bo.findAll();
		for(Produto produto : produtos) {
			System.out.println(produto);
		}
	}

	@Test
	@Order(5)
	public void activate() {
		Produto produto = bo.findById(1L);
		bo.activate(produto);
	}
	
	@Test
	@Order(6)
	public void inactivate() {
		Produto produto = bo.findById(1L);
		bo.inactivate(produto);
	}
	
	@Test
	@Order(6)
	public void remove() {
		Produto produto = bo.findById(1L);
		bo.remove(produto);
	}
	
}
