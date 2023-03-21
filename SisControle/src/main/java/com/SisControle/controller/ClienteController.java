package com.SisControle.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SisControle.bo.ClienteBO;
import com.SisControle.model.Cliente;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	ClienteBO clienteBO;


	@GetMapping("/novo")
	public ModelAndView novo(ModelMap model) {
		model.addAttribute("cliente", new Cliente());
		return new ModelAndView("/cliente/formulario", model);
	}

	
	@PostMapping("")
	public String salva(@Valid @ModelAttribute Cliente cliente, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			System.out.println(result);
			return "/cliente/formulario";
		}
		if (cliente.getId() == null) {
			clienteBO.insert(cliente);
			attr.addFlashAttribute("feedback", "Cliente registrado com sucesso");
		} else {
			clienteBO.update(cliente);
			attr.addFlashAttribute("feedback", "Cliente atualizado com sucesso");
		}
		return "redirect:/clientes";
	}

	
	@GetMapping("")
	public ModelAndView lista(ModelMap model) {
		model.addAttribute("clientes", clienteBO.findAll());
		return new ModelAndView("/cliente/lista", model);
	}

	
	@GetMapping("/edita/{id}")
	public ModelAndView edita(@PathVariable("id") Long id, ModelMap model) {
		try {
			model.addAttribute("cliente", clienteBO.findById(id));
		} catch (Exception error) {
			error.printStackTrace();
		}
		return new ModelAndView("/cliente/formulario", model);
	}

	
	@GetMapping("/inativa/{id}")
	public String inativa(@PathVariable("id") Long id, RedirectAttributes attr) {
		try {
			Cliente cliente = clienteBO.findById(id);
			clienteBO.inactivate(cliente);
			attr.addFlashAttribute("feedback", "Cliente inativado com sucesso");
		} catch (Exception error) {
			error.printStackTrace();
		}
		return "redirect:/clientes";
	}

	
	@GetMapping("/ativa/{id}")
	public String ativa(@PathVariable("id") Long id, RedirectAttributes attr) {
		try {
			Cliente cliente = clienteBO.findById(id);
			clienteBO.activate(cliente);
			attr.addFlashAttribute("feedback", "Cliente ativado com sucesso");
		} catch (Exception error) {
			error.printStackTrace();
		}
		return "redirect:/clientes";
	}

}
