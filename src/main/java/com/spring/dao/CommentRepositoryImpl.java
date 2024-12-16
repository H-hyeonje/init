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
		int lastPage = Math.max((page - 1) * 5, 0);
		CommetRowMapper commetRowMapper=new CommetRowMapper();
		Map<String, Object> result=new HashMap<String, Object>();
		List<Comment> commnets=new ArrayList<Comment>();
		System.out.println(page);
		String commentsSQL="SELECT * FROM (SELECT * FROM comment WHERE p_unique =? ORDER BY commentDate) AS subquery ORDER BY commentDate asc LIMIT ?, 5";
		String commentnumSQL="select count(*) from comment where p_unique=?";
		commnets=template.query(commentsSQL,commetRowMapper,new Object[] {P_unique,lastPage});
		int pagenum=template.queryForObject(commentnumSQL,Integer.class,P_unique );
		result.put("commnets", commnets);
		result.put("pagenum", pagenum);
		return result;
	}



	@Override
	public int updateLike(int c_unique) {
		System.out.println("like"+c_unique);
		String likeupdateSQL="UPDATE comment SET commentLikes = commentLikes + 1 WHERE c_unique = ?";
		template.update(likeupdateSQL,c_unique);
		String likeSQL="select commentLikes from comment where c_unique=?";
		int like=template.queryForObject(likeSQL,Integer.class,c_unique);
		return like;
	}



	@Override
	public int commentDelete(Comment comment) {
	    String commentDeleteSQL = "DELETE FROM comment WHERE c_unique = ?";
	    String commentnumSQL = "SELECT COUNT(*) FROM comment WHERE p_unique = ?";
	    template.update(commentDeleteSQL, comment.getC_unique());
	    int commentnum = template.queryForObject(commentnumSQL, Integer.class, comment.getP_unique());
	    System.out.println("남은 댓글 수: " + commentnum);
	    return commentnum;
	}
	
	

}