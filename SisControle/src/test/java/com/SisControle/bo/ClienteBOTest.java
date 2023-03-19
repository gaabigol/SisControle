package com.SisControle.bo;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SisControle.model.Cliente;
import com.SisControle.model.enums.Sexo;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ClienteBOTest {

	@Autowired
	ClienteBO bo;

	@Test
	@Order(1)
	public void insert() {
		Cliente cliente = new Cliente();
		cliente.setNome("José Silva");
		cliente.setCpf("0123456789");
		cliente.setDataNascimento(LocalDate.of(2000, 1, 8));
		cliente.setSexo(Sexo.MASCULINO);
		cliente.setTelefone("01234567");
		cliente.setCelular("01234567");
		cliente.setAtivo(true);
		bo.insert(cliente);
	}

	@Test
	@Order(2)
	public void findById() {
		Cliente cliente = bo.findById(1L);
		System.out.println(cliente);
	}

	@Test
	@Order(3)
	public void update() {
		Cliente cliente = bo.findById(1L);
		cliente.setCelular("122555");
		bo.update(cliente);
	}

	@Test
	@Order(4)
	public void findAll() {
		List<Cliente> clientes = bo.findAll();
		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}
	}

	@Test
	@Order(5)
	public void inactivate() {
		Cliente cliente = bo.findById(1L);
		bo.inactivate(cliente);
	}

	@Test
	@Order(6)
	public void activate() {
		Cliente cliente = bo.findById(1L);
		bo.activate(cliente);
	}

	@Test
	@Order(7)
	public void remove() {
		Cliente cliente = bo.findById(1L);
		bo.remove(cliente);
	}
}
