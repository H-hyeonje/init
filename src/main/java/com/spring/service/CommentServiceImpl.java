package com.spring.service;

import java.util.Map;

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
	public Map<String, Object> getcommentList(int P_unique,int page) {
		Map<String, Object> result=commentRepository.getcommentList(P_unique,page);
		return result;
	}
	@Override
	public int updateLike(int c_unique) {
		int like=commentRepository.updateLike(c_unique);
		return like;
	}
	@Override
	public int commentDelete(Comment comment) {
		int pagenum=commentRepository.commentDelete(comment);
		return pagenum;
	}
	
	
	
}
