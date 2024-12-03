package com.spring.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSourceExtensionsKt;
import org.springframework.stereotype.Repository;

import com.spring.domain.*;
@Repository
public class PostRepositoryImpl implements PostRepository{
	
	RowMapper<Post> postRowMapper=new PostRowMapper();
	private JdbcTemplate template;
	
	@Autowired
	public void setJdbctemplate(DataSource dataSource) {
		this.template=new JdbcTemplate(dataSource);
	}
	
	
	@Override
	public void PostSave(Post post) {
		System.out.println(post.getPublishDate());
		System.out.println(TimeZone.getDefault().getID());
		String SQL="Insert into post(id,title,contents,region,isPrivate,satisfaction,PublishDate)"+"values(?,?,?,?,?,?,?)";
		template.update(SQL,post.getId(),
							post.getTitle(),
							post.getContents(),
							post.getRegion(),
							post.getIsPrivate(),
							post.getSatisfaction(),
							post.getPublishDate()
				
				);
		
	}

	@Override
	public Post PostRead(int p_unique) {
		//나중에 블린값추가
	    String SelectSQL = "select * from Post where p_unique=? order by publishDate desc";//And isPrivate=1
	    String UdateSQL = "UPDATE POST SET view=view+1 WHERE p_unique=?";//And isPrivate=1
		Post onePost = template.queryForObject(SelectSQL,new Object[] {p_unique},postRowMapper);
		template.update(UdateSQL,p_unique);
	    return onePost;
	}

	@Override
	public List<Post> AllRead() {
		List<Post> AllPost=new ArrayList<Post>();
		String SQL ="select * from Post where isPrivate=1 order by publishDate desc";
		AllPost=template.query(SQL, postRowMapper);
		return AllPost;
	}

	@Override
	public List<Post> getBoard(String id) {
		System.out.println("리파지토리"+id);
		List<Post> Board=new ArrayList<Post>();
		String SQL="SELECT * FROM POST WHERE id=?";//And isPrivate=1
		
		Board=template.query(SQL,new Object[]{id},postRowMapper);
		return Board;
	}

	
	
	
}
