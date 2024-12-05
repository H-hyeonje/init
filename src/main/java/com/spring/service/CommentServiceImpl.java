package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.CommentRepository;
import com.spring.domain.Comment;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	CommentRepository commentRepository;
	@Override
	public void addComment(Comment comment) {
		commentRepository.addComment(comment);
		
	}

}
