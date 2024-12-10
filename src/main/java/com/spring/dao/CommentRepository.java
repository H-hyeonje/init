package com.spring.dao;




import java.util.Map;

import com.spring.domain.Comment;

public interface CommentRepository {
	public void addComment(Comment comment); 
	public Map<String, Object> getcommentList(int P_unique,int page);
	public int updateLike(int c_unique);
	public int commentDelete(Comment comment);
}
