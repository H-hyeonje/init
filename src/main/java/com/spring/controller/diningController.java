package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.domain.Dining;

@Controller
public class diningController {
	
	@GetMapping("/DiningEdit")
	public String DiningEdit() {
		return "DiningForm";
	}
	
	@PostMapping("/DiningCreate")
	public String DiningCreate(@ModelAttribute Dining dining,Model model) {
		
		
		return "DiningView";
	}
}
