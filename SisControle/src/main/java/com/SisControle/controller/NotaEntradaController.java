package com.SisControle.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SisControle.bo.FornecedorBO;
import com.SisControle.bo.NotaEntradaBO;
import com.SisControle.model.NotaEntrada;

@Controller
@RequestMapping("/nota-entrada")
public class NotaEntradaController {

	@Autowired
	private NotaEntradaBO notaEntradaBO;

	@Autowired
	private FornecedorBO fornecedorBO;

	@GetMapping("/novo")
	public ModelAndView novo(ModelMap model) {
		model.addAttribute("notaEntrada", new NotaEntrada());
		model.addAttribute("fornecedores", fornecedorBO.findAll());
		return new ModelAndView("/nota-entrada/formulario", model);
	}

	@PostMapping
	public String salva(@Valid @ModelAttribute NotaEntrada notaEntrada, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "/nota-entrada/formulario";
		}
		if (notaEntrada.getId() == null) {
			notaEntradaBO.insert(notaEntrada);
			attr.addFlashAttribute("feedback", "Nota de entrada salva com sucesso");
		} else {
			notaEntradaBO.update(notaEntrada);
			attr.addFlashAttribute("feedback", "Os dados da nota foram atualizados com sucesso");
		}
		return "redirect:/nota-entrada";
	}

}
