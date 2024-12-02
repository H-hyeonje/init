package com.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.domain.Post;

public class PostRowMapper implements RowMapper<Post>{

	@Override
	public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
		Post post=new Post();
		post.setId(rs.getString(1));
		post.setTitle(rs.getString(2));
		post.setContents(rs.getString(3));
		post.setPublishDate(rs.getDate(4));
		post.setView(rs.getInt(5));
		post.setLikes(rs.getInt(6));
		post.setRegion(rs.getString(7));
		post.setPrivate(rs.getBoolean(8));
		post.setP_unique(rs.getInt(9));
		post.setCommentDate(rs.getDate(10));
		post.setCommentLikes(rs.getInt(11));
		post.setCommentIsAllowed(rs.getBoolean(12));
		post.setSatisfaction(rs.getInt(13));

		return post;
	}

}
