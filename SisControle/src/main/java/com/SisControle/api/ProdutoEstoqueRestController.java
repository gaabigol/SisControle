package com.SisControle.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.SisControle.bo.ProdutoBO;
import com.SisControle.bo.ProdutoEstoqueBO;
import com.SisControle.model.Produto;
import com.SisControle.model.ProdutoEstoque;

@RestController
public class ProdutoEstoqueRestController {

	@Autowired
	private ProdutoBO produtoBO;
	@Autowired
	private ProdutoEstoqueBO produtoEstoqueBO;

	@GetMapping("api/estoque")
	public List<ProdutoEstoque> findAll() {
		return produtoEstoqueBO.findAll();
	}

	@GetMapping("/api/estoque/{id}")
	public ProdutoEstoque findById(@PathVariable Long id) {
		return produtoEstoqueBO.findById(id);
	}

	@PostMapping("/api/estoque")
	public ProdutoEstoque insert(@RequestBody ProdutoEstoque produtoEstoque) {
		Produto produto = produtoBO.findById(produtoEstoque.getProduto().getId());
		produtoEstoque.setProduto(produto);
		produtoEstoqueBO.insert(produtoEstoque);
		return produtoEstoque;
	}

	@PutMapping("/api/estoque/{id}")
	public ProdutoEstoque update(@PathVariable Long id, @RequestBody ProdutoEstoque produtoEstoque) {
		produtoEstoque.setId(id);
		produtoEstoque.setProduto(produtoBO.findById(produtoEstoque.getProduto().getId()));
		produtoEstoqueBO.update(produtoEstoque);
		return produtoEstoque;
	}

	@DeleteMapping("/api/estoque/{id}")
	public ProdutoEstoque remove(@PathVariable Long id) {
		ProdutoEstoque produtoEstoque = produtoEstoqueBO.findById(id);
		produtoEstoqueBO.remove(produtoEstoque);
		return produtoEstoque;
	}
}
