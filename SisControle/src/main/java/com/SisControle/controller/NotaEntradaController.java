package com.SisControle.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SisControle.bo.FornecedorBO;
import com.SisControle.bo.NotaEntradaBO;
import com.SisControle.bo.ProdutoBO;
import com.SisControle.model.NotaEntrada;
import com.SisControle.model.NotaEntradaItem;

@Controller
@RequestMapping("/notas-entrada")
public class NotaEntradaController {

	@Autowired
	private NotaEntradaBO notaEntradaBO;

	@Autowired
	private FornecedorBO fornecedorBO;

	@Autowired
	ProdutoBO produtoBO;

	@GetMapping("/novo")
	public ModelAndView novo(ModelMap model) {
		model.addAttribute("notaEntrada", new NotaEntrada());
		model.addAttribute("fornecedores", fornecedorBO.findAll());
		return new ModelAndView("/nota-entrada/formulario", model);
	}

	@PostMapping("")
	public String salva(@Valid @ModelAttribute NotaEntrada notaEntrada, BindingResult result, RedirectAttributes attr,
			ModelMap model) {

		if (notaEntrada.getFornecedor().getId() == null)
			result.rejectValue("fornecedor", "field.required");

		if (result.hasErrors()) {
			model.addAttribute("fornecedores", fornecedorBO.findAll());
			return "/nota-entrada/formulario";
		}

		if (notaEntrada.getId() == null) {
			notaEntradaBO.insert(notaEntrada);
			attr.addFlashAttribute("feedback", "A nota de entrada foi cadastrada com sucesso");
		} else {
			notaEntradaBO.update(notaEntrada);
			attr.addFlashAttribute("feedback", "Os dados da nota de entrada foram atualizados com sucesso");
		}

		return "redirect:/notas-entrada";
	}

	@GetMapping("")
	public ModelAndView lista(ModelMap model) {
		model.addAttribute("notas", notaEntradaBO.findAll());
		return new ModelAndView("/nota-entrada/lista", model);
	}

	@GetMapping("/{id}/item")
	public ModelAndView produto(@PathVariable("id") Long id, ModelMap model) {
		NotaEntradaItem nei = new NotaEntradaItem();
		NotaEntrada ne = notaEntradaBO.findById(id);
		nei.setNotaEntrada(ne);
		model.addAttribute("notaEntradaItem", nei);
		model.addAttribute("produtos", produtoBO.findAll());
		return new ModelAndView("/nota-entrada-item/formulario", model);
	}

	@GetMapping("/edita/{id}")
	public ModelAndView edita(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("notaEntradaItem", new NotaEntradaItem());
		model.addAttribute("fornecedores", fornecedorBO.findAll());
		model.addAttribute("notaEntrada", notaEntradaBO.findById(id));
		return new ModelAndView("/nota-entrada/formulario", model);
	}

	@GetMapping("/remove/{id}")
	public String remove(@PathVariable("id") Long id, RedirectAttributes attr) {
		NotaEntrada ne = notaEntradaBO.findById(id);
		notaEntradaBO.remove(ne);
		attr.addFlashAttribute("feedback", "Nota entrada removida com sucesso");
		return "redirect:/notas-entrada";
	}
}
