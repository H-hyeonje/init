package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.domain.Post;
import com.spring.service.PostService;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class PostController {
	
	@Autowired
	PostService postService;
	
	@GetMapping("/")
	public String indax() {
		
		return "index";
	}
	
	@GetMapping("/Post")
	public String Posts() {
		
		return "PostForm";
	}
	
	@PostMapping("/Postadd")
	public String Postviews(@ModelAttribute Post post ,Model model) {
		postService.PostSave(post);
		return "redirect:/PostBoard";
	}
	
	@GetMapping("/Postview/{title}/{p_unique}")
	public String Postviewss(@PathVariable String title, @PathVariable int p_unique,Model model) {
		Post aa=postService.PostRead(title,p_unique);
		model.addAttribute("Post",aa);
		return "Postview";
	}
	
	
	@GetMapping("/PostBoard")
	public String PostBoard(Model model) {
		List<Post> AllPost= postService.AllRead();
		model.addAttribute("All",AllPost);
		return "PostBoard";
	}
	
	
	
}
