package com.spring.dao;





import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.spring.domain.Comment;

public class CommentRepositoryImpl implements CommentRepository {
	
private JdbcTemplate template;
	
	public void setJdbctemplate(DataSource dataSource) {
		this.template=new JdbcTemplate(dataSource);
	}
	
	
	
	@Override
	public void addComment(Comment comment) {
		String SQL="Insert into comment(p_unique,id,comments,commentDate) values(?,?,?,?)";
		template.update(SQL,comment.getP_unique(),comment.getId(),comment.getComments(),comment.getCommentDate());
	}

}
