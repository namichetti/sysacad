package com.sysacad.alkemy.controller;


import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sysacad.alkemy.entity.Materia;
import com.sysacad.alkemy.service.IMateriaService;

@Controller
@RequestMapping("/materia")
@SessionAttributes("materia")
public class MateriaController {
	
	@Autowired
	private IMateriaService materiaService;
	
	@GetMapping("/")
	public String getAll(Model model) {
		List<Materia> materias = materiaService.getAll();
		Collections.sort(materias, (x, y) -> x.getNombre().compareToIgnoreCase(y.getNombre()));
		model.addAttribute("titulo","Lista de Materias");
		model.addAttribute("materias", materias);
		return "index";
	}
	
	@GetMapping("/{id}")
	public String getMateria(@PathVariable Long id, Model model, RedirectAttributes flash) {
		Materia materia = materiaService.getOne(id);
		
		if(id == null || id <1){
			flash.addFlashAttribute("danger","El ID de la matería es inválido.");
			return "redirect:/materia/";
		}
		
		if(materia == null) {
			flash.addFlashAttribute("danger","La materia no existe.");
			return "redirect:/materia/";
		}	
		model.addAttribute("titulo","Materia");
		model.addAttribute("subTitulo","Profesores");
		model.addAttribute("materia", materia);
		
		return "ver_materia";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/delete/{id}")
	public String deleteMateria(@PathVariable Long id, Model model, RedirectAttributes flash) {
		
		if(id == null || id <1){
			flash.addFlashAttribute("danger","El ID de la matería es inválido.");
			return "redirect:/materia/";
		}
		
		materiaService.deleteOne(id);
		flash.addFlashAttribute("success","La materia fue dada de baja.");	
		return "redirect:/materia/";
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/guardar")
	public String saveMateria(@Valid Materia materia, BindingResult result, Model model, RedirectAttributes flash, SessionStatus session) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo","Formulario de Materia");	
			return "formulario_materia";
		}
		
		String mensaje = materia.getId() != null ? "La materia fue actualizada":"La materia fue dada de alta";
		flash.addFlashAttribute("success",mensaje);	
		materiaService.saveOne(materia);
		session.setComplete();
		return "redirect:/materia/";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/alta")
	public String uploadMateria(Materia materia, Model model) {
		model.addAttribute("titulo", "Formulario de Materia");
		model.addAttribute("materia", materia);
		return "formulario_materia";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/editar/{id}")
	public String updateMateria(@PathVariable Long id, Model model,RedirectAttributes flash) {
		
		Materia materia = null;
		
		if(id == null || id <1){
			flash.addFlashAttribute("danger","El ID de la matería es inválido.");
			return "redirect:/materia/";
		}
		
		materia = materiaService.getOne(id);
		
		if(materia == null) {
			flash.addFlashAttribute("danger","La materia no existe.");
			return "redirect:/materia/";
		}	
		
		model.addAttribute("titulo", "Formulario de Materia");
		model.addAttribute("materia", materia);
		return "formulario_materia";
	}
	
	
	
}
