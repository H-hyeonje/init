package com.spring.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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
	public void savePost(Post post) {
		String SQL="Insert into post(id,title,contents,region,isPrivate,satisfaction,PublishDate) values(?,?,?,?,?,?,?)";
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
	public Post getPost(int p_unique) {
		//나중에 블린값추가
	    String SelectSQL = "select * from Post where p_unique=? order by publishDate desc";//And isPrivate=1
	    String UdateSQL = "UPDATE POST SET view=view+1 WHERE p_unique=?";//And isPrivate=1
		Post onePost = template.queryForObject(SelectSQL,new Object[] {p_unique},postRowMapper);
		template.update(UdateSQL,p_unique);
	    return onePost;
	}

	@Override
	public Map<String,Object> getAllPosts(int ps) {
		Map<String,Object> result =new HashMap<String, Object>();
		List<Post> posts=new ArrayList<Post>();
		String SQLs ="SELECT COUNT(*) FROM Post WHERE isPrivate = 1;";
		String SQL ="select * from Post where isPrivate=1 order by publishDate desc limit ?,?";
		posts=template.query(SQL,new Object[] {(ps-1)*5,5}, postRowMapper);
		int totalPages =template.queryForObject(SQLs,Integer.class);
		result.put("posts", posts);
		result.put("totalPages", totalPages);
		return result;
	}

	@Override
	public Map<String, Object> getUserPosts(String id,int ps) {
		System.out.println("리파지토리"+id);
		List<Post> Board=new ArrayList<Post>();
		String SQL="SELECT * FROM POST WHERE id=? order by publishDate desc limit ?,?";//And isPrivate=1
		Map<String, Object> result=new HashMap<String, Object>();
		String SQLs="select count(*) FROM POST WHERE id=? order by publishDate desc";
		
		Board=template.query(SQL,new Object[]{id,(ps-1)*5,5},postRowMapper);
		int pagenum=template.queryForObject(SQLs,new Object[] {id},Integer.class);
		result.put("Board", Board);
		result.put("pagenum", pagenum);
		
		return result;
	}


	@Override
	public int updatePost(Post post) {
		String SQL="UPDATE post SET title = ?, contents = ?, isPrivate= ?, region=?, satisfaction=? WHERE id=?";
		template.update(SQL,post.getTitle(),post.getContents(),post.getIsPrivate(),post.getRegion(),post.getSatisfaction(),post.getId());
		
		return 1;
	}


	@Override
	public void deletePost(int p_unique) {
		String SQL="delete from post where p_unique=?";
		template.update(SQL,p_unique);
		
	}

	
	
	
}
