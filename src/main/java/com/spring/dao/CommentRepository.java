package com.spring.dao;



import java.util.List;

import com.spring.domain.Comment;

public interface CommentRepository {
	public void addComment(Comment comment); 
	public List<Comment> getCommentsByPostId(int P_unique);
}
