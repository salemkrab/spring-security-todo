package fr.m2i.todoihm.controller;


import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.m2i.todoihm.model.Todo;
import fr.m2i.todoihm.service.TodoService;





@Controller
@RolesAllowed("*")
public class PageController {
	
	@Value("${spring.application.name}")
	private String nameApp;
	
	
	@Autowired
	private TodoService ts;
	
	@GetMapping(value="/failed")
	public String failed() {
		
		return "failed";
	}
	
	@GetMapping(value="/forbidden")
	public String forbidden(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("username", auth.getPrincipal());
		return "forbidden";
	}
	
	@GetMapping(
			name="homePage",
			value="/"
			)
	public String home(Model model, @ModelAttribute Todo todo, HttpSession session) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("username", auth.getPrincipal());

		session.setAttribute("message","hello");
		model.addAttribute("hello", nameApp);
		model.addAttribute("todos", ts.getTodos());

		return "home";
	}
	
	@PostMapping("/")
	public String homePost(@ModelAttribute Todo todo) {


		ts.getTodos().add(todo);
		
		return "redirect:/";
	}
	@GetMapping("/login")
	public String loginGet() {
		return "login";
	}



	@GetMapping("/logout")
	public String deco(HttpServletRequest request, Model model) {
		SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
		request.getSession(true).invalidate();
		return "redirect:/";
	}
	
	@PostMapping("/delete")
	@RolesAllowed("*")
	public String delete(@ModelAttribute Todo todo) {
		ts.deleteTodo(todo);


		return "redirect:/";
	}
	
	

}
