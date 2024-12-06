package com.spring.dao;





import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.domain.Comment;
@Repository
public class CommentRepositoryImpl implements CommentRepository {
	
private JdbcTemplate template;
	@Autowired
	public void setJdbctemplate(DataSource dataSource) {
		this.template=new JdbcTemplate(dataSource);
	}
	
	
	
	@Override
	public void addComment(Comment comment) {
		String UpdateSQL="Insert into comment(p_unique,id,comments,commentDate) values(?,?,?,?)";
		template.update(UpdateSQL,comment.getP_unique(),comment.getId(),comment.getComments(),comment.getCommentDate());
	}



	@Override
	public List<Comment> getCommentsByPostId(int P_unique) {
		CommetRowMapper commetRowMapper=new CommetRowMapper();
		List<Comment> commnets=new ArrayList<Comment>();
		String commentsSQL="select * from comment where p_unique=? order by commentDate";
		commnets=template.query(commentsSQL, new Object[] {P_unique}, commetRowMapper);
		return commnets;
	}

}
