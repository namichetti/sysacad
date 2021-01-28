package com.sysacad.alkemy.controller;

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

import com.sysacad.alkemy.entity.Profesor;
import com.sysacad.alkemy.service.IProfesorService;


@Controller
@RequestMapping("/profesor")
@SessionAttributes("profesor")
public class ProfesorController {
	
	@Autowired
	private IProfesorService profesorService;

	@GetMapping("/")
	public String getAll(Model model) {
		List<Profesor> profesores = profesorService.getAll();
		model.addAttribute("titulo","Lista de Profesores");
		model.addAttribute("profesores", profesores);
		return "lista_profesores";
	}
	
	@GetMapping("/{id}")
	public String getProfesor(@PathVariable Long id, Model model, RedirectAttributes flash) {
		Profesor profesor = profesorService.getOne(id);
		
		if(id == null || id <1){
			flash.addFlashAttribute("danger","El ID del profesor es inválido.");
			return "redirect:/profesor/";
		}
		
		if(profesor == null) {
			flash.addFlashAttribute("danger","El profesor no existe.");
			return "redirect:/profesor/";
		}	
		model.addAttribute("titulo","Profesor");
		model.addAttribute("profesor", profesor);
		
		return "ver_profesor";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/delete/{id}")
	public String deleteProfesor(@PathVariable Long id, Model model, RedirectAttributes flash) {
		
		if(id == null || id <1){
			flash.addFlashAttribute("danger","El ID del profesor es inválido.");
			return "redirect:/profesor/";
		}
		
		profesorService.deleteOne(id);
		flash.addFlashAttribute("success","La profesor fue dada de baja.");	
		return "redirect:/profesor/";
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/guardar")
	public String saveProfesor(@Valid Profesor profesor, BindingResult result, Model model, RedirectAttributes flash, SessionStatus session) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo","Formulario de profesor");	
			return "formulario_profesor";
		}
		
		String mensaje = profesor.getId() != null ? "La profesor fue actualizada":"La profesor fue dada de alta";
		flash.addFlashAttribute("success",mensaje);	
		profesorService.saveOne(profesor);
		session.setComplete();
		return "redirect:/profesor/";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/alta")
	public String uploadProfesor(Profesor profesor, Model model) {
		model.addAttribute("titulo", "Formulario de profesor");
		model.addAttribute("profesor", profesor);
		return "formulario_profesor";
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/editar/{id}")
	public String updateProfesor(@PathVariable Long id, Model model,RedirectAttributes flash) {
		
		Profesor profesor = null;
		
		if(id == null || id <1){
			flash.addFlashAttribute("danger","El ID del profesor es inválido.");
			return "redirect:/profesor/";
		}
		
		profesor = profesorService.getOne(id);
		
		if(profesor == null) {
			flash.addFlashAttribute("danger","El profesor no existe.");
			return "redirect:/profesor/";
		}	
		
		model.addAttribute("titulo", "Formulario de Profesor");
		model.addAttribute("profesor", profesor);
		return "formulario_profesor";
	}

}
