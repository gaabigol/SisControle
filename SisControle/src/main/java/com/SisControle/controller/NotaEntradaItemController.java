package com.SisControle.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.SisControle.bo.NotaEntradaBO;
import com.SisControle.bo.NotaEntradaItemBO;
import com.SisControle.bo.ProdutoBO;
import com.SisControle.model.NotaEntrada;
import com.SisControle.model.NotaEntradaItem;

@Controller
@RequestMapping("/nota-entrada-item")
public class NotaEntradaItemController {

	@Autowired
	private ProdutoBO produtoBO;

	@Autowired
	private NotaEntradaBO notaEntradaBO;

	@Autowired
	private NotaEntradaItemBO notaEntradaItemBO;

	@PostMapping("")
	public String salva(@Valid @ModelAttribute NotaEntradaItem notaEntradaItem, BindingResult result,
			RedirectAttributes attr, ModelMap model) {

		Long produtoId = notaEntradaItem.getProduto().getId();
		if (produtoId == null)
			result.rejectValue("produto", "field.required");

		if (notaEntradaItemBO.itemJaAdicionado(notaEntradaItem)) {
			result.rejectValue("produto", "duplicate");
		}

		if (result.hasErrors()) {
			model.addAttribute("produtos", produtoBO.findAll());
			return "/nota-entrada-item/formulario";
		}

		NotaEntrada notaEntrada = notaEntradaBO.findById(notaEntradaItem.getNotaEntrada().getId());
		notaEntradaItem.setNotaEntrada(notaEntrada);

		if (notaEntradaItem.getId() == null) {
			notaEntradaItemBO.insert(notaEntradaItem);
			attr.addFlashAttribute("feedcack", "Produto adicionado com sucesso!");
		} else {
			notaEntradaItemBO.update(notaEntradaItem);
			attr.addFlashAttribute("feedcack", "Produto atualizado com sucesso!");
		}

		Long notaEntradaId = notaEntradaItem.getNotaEntrada().getId();
		return "redirect:/notas-entrada/edita/" + notaEntradaId;
	}

	@GetMapping("/edita/{id}")
	public ModelAndView edita(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("notaEntradaItem", notaEntradaItemBO.findById(id));
		model.addAttribute("produtos", produtoBO.findAll());
		return new ModelAndView("/nota-entrada-item/formulario", model);
	}

	@GetMapping("/remove/{id}")
	public String remove(@PathVariable("id") Long id, RedirectAttributes attr) {
		Long notaId = 0L;
		NotaEntradaItem notaEntradaItem = notaEntradaItemBO.findById(id);
		notaId = notaEntradaItem.getNotaEntrada().getId();
		notaEntradaItemBO.remove(notaEntradaItem);
		attr.addAttribute("feedback", "Item removido com sucesso!");
		return "redirect:/notas-entrada/edita/" + notaId;
	}

}
