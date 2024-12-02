package com.spring.dao;


import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.domain.*;
@Repository
public class PostRepositoryImpl implements PostRepository{
	
	
	private JdbcTemplate template;
	
	@Autowired
	public void setJdbctemplate(DataSource dataSource) {
		this.template=new JdbcTemplate(dataSource);
	}
	
	@Override
	public void PostSave(Post post) {
		String SQL="Insert into post(id,title,contents,region,isPrivate,satisfaction)"+"values(?,?,?,?,?,?)";
		template.update(SQL,post.getId(),
							post.getTitle(),
							post.getContents(),
							post.getRegion(),
							post.isPrivate(),
							post.getSatisfaction()
				
				);
		
	}

	@Override
	public Post PostRead(String title,int p_unique) {
	    String SQL = "select * from Post where title = ? AND p_unique=?";
		Post onePost = (Post) template.queryForObject(SQL, new Object[]{title, p_unique}, new PostRowMapper());
	    return onePost;
	}

	@Override
	public List<Post> AllRead() {
		List<Post> AllPost=new ArrayList<Post>();
		String SQL ="select * from Post";
		AllPost=template.query(SQL, new PostRowMapper());
		return AllPost;
	}


	
	
}
