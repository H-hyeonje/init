package com.spring.service;

import java.util.List;

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
	@Override
	public List<Comment> getCommentsByPostId(int P_unique) {
		List<Comment> commnets=commentRepository.getCommentsByPostId(P_unique);
		return commnets;
	}

}
