package com.spring.controller;



import com.spring.domain.Comment;
import com.spring.service.CommentService;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class commentController {
	
	@Autowired
	CommentService commentService;
	@PostMapping(value = "/addComment", consumes = "application/json")
	public List<Comment> addComment(@RequestBody Comment comment,Model model) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		comment.setCommentDate(timestamp);
		List<Comment> comments=commentService.getCommentsByPostId(comment.getP_unique());;
		
		return comments;
	}
	
	
	
	
}
