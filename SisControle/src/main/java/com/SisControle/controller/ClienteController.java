package com.SisControle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.SisControle.bo.ClienteBO;
import com.SisControle.model.Cliente;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	ClienteBO clienteBO;

	// controller OKAY
	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo(ModelMap model) {
		model.addAttribute("cliente", new Cliente());
		return new ModelAndView("/cliente/formulario", model);
	}

	// Controller OKAY
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String salva(@ModelAttribute Cliente cliente) {
		if (cliente.getId() == null) {
			clienteBO.insert(cliente);
		} else {
			clienteBO.update(cliente);
		}
		return "cliente/formulario";
	}

	// Controller OKAY
	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public ModelAndView lista(ModelMap model) {
		model.addAttribute("clientes", clienteBO.findAll());
		return new ModelAndView("/cliente/lista", model);
	}

	// Controller OKAY
	@RequestMapping(value = "/edita/{id}", method = RequestMethod.GET)
	public ModelAndView edita(@PathVariable("id") Long id, ModelMap model) {
		try {
			model.addAttribute("cliente", clienteBO.findById(id));
		} catch (Exception error) {
			error.getStackTrace();
		}
		return new ModelAndView("/cliente/formulario", model);
	}

	// Controller OKAY
	@RequestMapping(value = "/inativa/{id}", method = RequestMethod.GET)
	public String inativa(@PathVariable("id") Long id) {
		try {
			Cliente cliente = clienteBO.findById(id);
			clienteBO.inactivate(cliente);
		} catch (Exception error) {
			error.getStackTrace();
		}
		return "redirect:/clientes/lista";
	}

	// Controller OKAY
	@RequestMapping(value = "/ativa/{id}", method = RequestMethod.GET)
	public String ativa(@PathVariable("id") Long id) {
		try {
			Cliente cliente = clienteBO.findById(id);
			clienteBO.activate(cliente);
		} catch (Exception error) {
			error.getStackTrace();
		}
		return "redirect:/clientes/lista";
	}

}
