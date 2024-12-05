package com.spring.controller;



import com.spring.domain.Comment;
import com.spring.service.CommentService;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class commentController {
	
	@Autowired
	CommentService commentService;
	@PostMapping(value = "/addComment", consumes = "application/json")
	public void addComment(@RequestBody Comment comment ) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		comment.setCommentDate(timestamp);
		
		commentService.addComment(comment);
	}
	
}
