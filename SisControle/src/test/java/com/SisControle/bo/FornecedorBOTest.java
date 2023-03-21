package com.SisControle.bo;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SisControle.model.Fornecedor;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class FornecedorBOTest {

	@Autowired
	FornecedorBO bo;

	@Test
	@Order(1)
	public void insert() {
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setCelular("1234567");
		fornecedor.setCnpj("38.532.721/0001-15");
		fornecedor.setEmail("gabigol_96@icloud.com");
		fornecedor.setTelefone("12345687");
		fornecedor.setRazaoSocial("comercio");
		bo.insert(fornecedor);
	}

	@Test
	@Order(2)
	public void findById() {
		Fornecedor fornecedor = bo.findById(1L);
		System.out.println(fornecedor);
	}

	@Test
	@Order(3)
	public void update() {
		Fornecedor fornecedor = bo.findById(1L);
		fornecedor.setCelular("1558888");
		bo.update(fornecedor);
	}

	@Test
	@Order(4)
	public void findAll() {
		List<Fornecedor> fornecedores = bo.findAll();
		for (Fornecedor fornecedor : fornecedores) {
			System.out.println(fornecedor);
		}
	}

	@Test
	@Order(5)
	public void inactivate() {
		Fornecedor fornecedor = bo.findById(1L);
		bo.inactivate(fornecedor);
	}

	@Test
	@Order(6)
	public void activate() {
		Fornecedor fornecedor = bo.findById(1L);
		bo.activate(fornecedor);
	}

	@Test
	@Order(7)
	public void remove() {
		Fornecedor fornecedor = bo.findById(1L);
		bo.remove(fornecedor);
	}
}
