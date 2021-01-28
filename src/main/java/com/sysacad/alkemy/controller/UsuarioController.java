package com.sysacad.alkemy.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sysacad.alkemy.dao.IUsuarioDao;
import com.sysacad.alkemy.entity.Materia;
import com.sysacad.alkemy.entity.Usuario;
import com.sysacad.alkemy.service.IMateriaService;
import com.sysacad.alkemy.service.IUsuarioService;

@Controller
@RequestMapping("/usuario")

public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IMateriaService materiaService;
	
	@Autowired
	private IUsuarioDao usuarioDao;
	

	@Secured("ROLE_USER")
	@PostMapping("/inscribirse/{id}")
	public String saveMateria(@PathVariable Long id, Model model, RedirectAttributes flash) {
		
		Materia materia = materiaService.getOne(id);
		Usuario usuario = usuarioDao.findByUsuario("alumno");
				
		if(id == null || id <1){
			flash.addFlashAttribute("danger","El ID de la matería es inválido.");
			return "redirect:/materia/";
		}
		
		if(materia == null) {
			flash.addFlashAttribute("danger","La materia no existe.");
			return "redirect:/materia/";
		}	
		
		
		List<Materia> materias = new ArrayList<Materia>();
		materias.add(materia);
		usuario.setMaterias(materias);
		usuarioService.saveOne(usuario);
		flash.addFlashAttribute("success","Se ha inscripto satisfactoriamente a la materia " + materia.getNombre());	
		return "redirect:/materia/";
	}
	
}
