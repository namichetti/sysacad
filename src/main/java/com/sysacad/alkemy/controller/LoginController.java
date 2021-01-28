package com.sysacad.alkemy.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(@RequestParam(name="error",required=false)String error, 
						@RequestParam(name="logout",required=false)String logout,
						Principal principal, Model model,RedirectAttributes flash) {
		if(principal!=null) {
			flash.addFlashAttribute("info","Ya ha iniciado sesión");
			return "redirect:/materia/";
		}
		
		
		if(error!=null) {
			model.addAttribute("danger", "Usuario o contraseña incorrecto/s");
			
		}
		
		if(logout!=null){
			model.addAttribute("info", "Ah salido exitosamente.");
		}
		
		
		return "login";
	}
}

