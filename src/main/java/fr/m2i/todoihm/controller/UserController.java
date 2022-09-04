package fr.m2i.todoihm.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.m2i.todoihm.model.Todo;


@Controller
@RequestMapping("/user/")
@RolesAllowed("USER")
public class UserController {
	
	
	
	@PostMapping("/")
	public String postPost(@ModelAttribute Todo todo) {
		
		System.out.println(todo.getNom());
		System.out.println(todo.getDescription());
		
		return "form";
	}
	
	@GetMapping("/")
	public String postGet(@ModelAttribute Todo todo, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("username", auth.getPrincipal());
		
		return "form";
	}

}
