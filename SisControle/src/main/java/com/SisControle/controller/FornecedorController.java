package com.SisControle.controller;

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

import com.SisControle.bo.FornecedorBO;
import com.SisControle.model.Fornecedor;

@Controller
@RequestMapping("/fornecedores")
public class FornecedorController {

	@Autowired
	private FornecedorBO fornecedorBO;

	@GetMapping("/novo")
	public ModelAndView novo(ModelMap model) {
		model.addAttribute("fornecedor", new Fornecedor());
		return new ModelAndView("/fornecedor/formulario", model);
	}

	@PostMapping("")
	public String salva(@Valid @ModelAttribute Fornecedor fornecedor, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			System.out.println(result);
			return "/fornecedor/formulario";
		}
		if (fornecedor.getId() == null) {
			fornecedorBO.insert(fornecedor);
			attr.addFlashAttribute("feedback", "Fornecedor registrado com sucesso");
		} else {
			fornecedorBO.update(fornecedor);
			attr.addFlashAttribute("feedback", "Fornecedor atualizado com sucesso");
		}
		return "redirect:/fornecedores";
	}

	@GetMapping("")
	public ModelAndView lista(ModelMap model) {
		model.addAttribute("fornecedores", fornecedorBO.findAll());
		return new ModelAndView("/fornecedor/lista", model);
	}

	@GetMapping("/edita/{id}")
	public ModelAndView edita(@PathVariable("id") Long id, ModelMap model) {
		try {
			model.addAttribute("fornecedor", fornecedorBO.findById(id));
		} catch (Exception error) {
			error.printStackTrace();
		}
		return new ModelAndView("/fornecedor/formulario", model);
	}

	@GetMapping("/inativa/{id}")
	public String inativa(@PathVariable("id") Long id, RedirectAttributes attr) {
		try {
			Fornecedor fornecedor = fornecedorBO.findById(id);
			fornecedorBO.inactivate(fornecedor);
			attr.addFlashAttribute("feedback", "Fornecedor inativado com sucesso");
		} catch (Exception error) {
			error.printStackTrace();
		}
		return "redirect:/fornecedores";
	}

	@GetMapping("/ativa/{id}")
	public String ativa(@PathVariable("id") Long id, RedirectAttributes attr) {
		try {
			Fornecedor fornecedor = fornecedorBO.findById(id);
			fornecedorBO.activate(fornecedor);
			attr.addFlashAttribute("feedback", "Fornecedor ativado com sucesso");
		} catch (Exception error) {
			error.printStackTrace();
		}
		return "redirect:/fornecedores";

	}
}
