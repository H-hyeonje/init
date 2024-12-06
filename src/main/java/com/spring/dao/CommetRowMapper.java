package com.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.domain.Comment;

public class CommetRowMapper implements RowMapper<Comment> {

	@Override
	public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Comment comment=new Comment();
		comment.setC_unique(rs.getInt(1));
		comment.setP_unique(rs.getInt(2));
		comment.setId(rs.getString(3));
		comment.setComments(rs.getString(4));
		comment.setCommentDate(rs.getTimestamp(5));
		comment.setCommentLikes(rs.getInt(6));
		return comment;
	}

}
