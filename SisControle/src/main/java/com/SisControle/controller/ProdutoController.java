package com.SisControle.controller;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SisControle.bo.ProdutoBO;
import com.SisControle.model.Produto;
import com.SisControle.model.enums.Categoria;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	ProdutoBO produtoBO;

	@GetMapping("/novo")
	public ModelAndView novo(ModelMap model) {
		model.addAttribute("produto", new Produto());
		model.addAttribute("categorias", Arrays.asList(Categoria.values()));
		return new ModelAndView("/produto/formulario", model);
	}

	@PostMapping("")
	public String salva(@Valid @ModelAttribute Produto produto, BindingResult result, RedirectAttributes attr,
			ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("categorias", Arrays.asList(Categoria.values()));
			return "/produto/formulario";
		}
		if (produto.getId() == null) {
			produtoBO.insert(produto);
			attr.addFlashAttribute("feedback", "Produto registrado com sucesso");
		} else {
			produtoBO.update(produto);
			attr.addFlashAttribute("feedback", "Produto atualizado com sucesso");
		}
		return "redirect:/produtos";
	}

	@GetMapping("")
	public ModelAndView lista(ModelMap model) {
		model.addAttribute("produtos", produtoBO.findAll());
		return new ModelAndView("/produto/lista", model);
	}

	@GetMapping("/edita/{id}")
	public ModelAndView edita(@PathVariable("id") Long id, ModelMap model) {
		try {
			model.addAttribute("produto", produtoBO.findById(id));
			model.addAttribute("categorias", Arrays.asList(Categoria.values()));
		} catch (Exception error) {
			error.printStackTrace();
		}
		return new ModelAndView("/produto/formulario", model);
	}

	@GetMapping("/inativa/{id}")
	public String inativa(@PathVariable("id") Long id, RedirectAttributes attr) {
		try {
			Produto produto = produtoBO.findById(id);
			produtoBO.inactivate(produto);
			attr.addFlashAttribute("feedback", "Produto inativado com sucesso");
		} catch (Exception error) {
			error.printStackTrace();
		}
		return "redirect:/produtos";
	}

	@GetMapping("/ativa/{id}")
	public String ativa(@PathVariable("id") Long id, RedirectAttributes attr) {
		try {
			Produto produto = produtoBO.findById(id);
			produtoBO.activate(produto);
			attr.addFlashAttribute("feedback", "Produto ativado com sucesso");
		} catch (Exception error) {
			error.printStackTrace();
		}
		return "redirect:/produtos";
	}

}
