package com.spring.dao;





import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

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
	public Map<String, Object> getcommentList(int P_unique,int page) {
		CommetRowMapper commetRowMapper=new CommetRowMapper();
		Map<String, Object> result=new HashMap<String, Object>();
		List<Comment> commnets=new ArrayList<Comment>();
		System.out.println(page);
		String commentsSQL="SELECT * FROM (SELECT * FROM comment WHERE p_unique =? ORDER BY commentDate) AS subquery ORDER BY commentDate asc LIMIT ?, 5";
		String commentnumSQL="select count(*) from comment where p_unique=?";
		commnets=template.query(commentsSQL, new Object[] {P_unique,page}, commetRowMapper);
		int pagenum=template.queryForObject(commentnumSQL,new Object[] {P_unique}, Integer.class);
		result.put("commnets", commnets);
		result.put("pagenum", pagenum);
		return result;
	}

}
