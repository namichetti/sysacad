package com.sysacad.alkemy.controller;

import java.time.LocalTime;
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
		
		int cupo = materia.getCupo() - 1;
		/*if(cupo >= 1) {
			materia.setCupo(cupo);
			materiaService.saveOne(materia);
			flash.addFlashAttribute("success","Se ha inscripto satisfactoriamente a la materia " + materia.getNombre());
		}else {
			flash.addFlashAttribute("danger","No hay cupo disponible para la materia");
		}*/
		
		if(cupo >= 1) {
			
		 materia.setCupo(cupo);
		 materiaService.saveOne(materia);
		 
		 LocalTime Horarioinicio = LocalTime.parse(materia.getHorarioInicio().toString()); 
	     LocalTime Horariofin = LocalTime.parse(materia.getHorarioFin().toString()); 
	     
	     for(Materia m: usuario.getMaterias()) {
	    	 
	    	 int resultadoHorarioInicio = Horarioinicio.compareTo(m.getHorarioFin()); 
	    	 int resultadoHorarioFin = Horariofin.compareTo(m.getHorarioInicio()); 
	    	 int resultadoHorarioFinConFin= Horariofin.compareTo(m.getHorarioFin());			 
	    	 int resultadoHorarioInicioConInicio = Horarioinicio.compareTo(m.getHorarioInicio());																	
	    	 
	    	 if (resultadoHorarioInicio > 0 &&  resultadoHorarioFin == 0 && resultadoHorarioFinConFin >0 && resultadoHorarioInicioConInicio >0)
	 				flash.addFlashAttribute("success","Se ha inscripto satisfactoriamente a la materia " + materia.getNombre());
		      else if (resultadoHorarioInicio > 0 &&  resultadoHorarioFin > 0 && resultadoHorarioFinConFin >0 && resultadoHorarioInicioConInicio >0)
					flash.addFlashAttribute("success","Se ha inscripto satisfactoriamente a la materia " + materia.getNombre());
		      else if (resultadoHorarioInicio == 0 &&  resultadoHorarioFin > 0 && resultadoHorarioFinConFin > 0 && resultadoHorarioInicioConInicio > 0) 
					flash.addFlashAttribute("success","Se ha inscripto satisfactoriamente a la materia " + materia.getNombre());
		      else if (resultadoHorarioInicio < 0 &&  resultadoHorarioFin < 0 && resultadoHorarioFinConFin <0 && resultadoHorarioInicioConInicio <0) 
					flash.addFlashAttribute("success","Se ha inscripto satisfactoriamente a la materia " + materia.getNombre());
		      else if (resultadoHorarioInicio < 0 &&  resultadoHorarioFin == 0 && resultadoHorarioFinConFin < 0 && resultadoHorarioInicioConInicio < 0) 
					flash.addFlashAttribute("success","Se ha inscripto satisfactoriamente a la materia " + materia.getNombre());
		      else 
					flash.addFlashAttribute("danger","No puede inscribirse a esta materia por superposición de horarios");
	     	}
		}else {
			flash.addFlashAttribute("danger","No hay cupo disponible para la materia");
		}
			
		List<Materia> materias = new ArrayList<Materia>();
		materias.add(materia);
		usuario.setMaterias(materias);
		usuarioService.saveOne(usuario);
		
		return "redirect:/materia/";
	}
	
}
