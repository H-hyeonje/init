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

import com.spring.controller.page;
import com.spring.domain.*;
@Repository
public class PostRepositoryImpl implements PostRepository{
	
	page Page=new page();
	private JdbcTemplate template;
	
	
	@Autowired
	public void setJdbctemplate(DataSource dataSource) {
		this.template=new JdbcTemplate(dataSource);
	}
	
	
	@Override
	public void savePost(Post post) {
		RowMapper<Post> postRowMapper=new PostRowMapper();
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
	public Map<String,Object> getPost(int p_unique) {
		Map<String,Object> Post=new HashMap<String, Object>();
		CommetRowMapper commetRowMapper=new CommetRowMapper();
		RowMapper<Post> postRowMapper=new PostRowMapper();
		//나중에 블린값추가
	    String SelectSQL = "select * from Post where p_unique=? order by publishDate desc";//And isPrivate=1
	    String UdateSQL = "UPDATE POST SET view=view+1 WHERE p_unique=?";//And isPrivate=1
	    String commentsSQL="SELECT * FROM comment WHERE p_unique = ? ORDER BY commentDate LIMIT ?,5";
	    String commentnumSQL="select count(*) from comment where p_unique=?";
		Post onePost = template.queryForObject(SelectSQL,new Object[] {p_unique},postRowMapper);
		int commentnum=template.queryForObject(commentnumSQL,new Object[] {p_unique},Integer.class);
		int count = 0;
		List<Integer> pagenum = Page.calculateTotalPages(commentnum);

		if (pagenum != null && !pagenum.isEmpty() && pagenum.get(pagenum.size() - 1) != null) {
		    count = (pagenum.get(pagenum.size() - 1) - 1) * 5; // 마지막 페이지 번호를 가져와서 계산
		    System.out.println(count);
		    if (count < 0) {
		        count = 0;  // count가 음수일 경우 0으로 처리
		    }
		} else {
		    // pagenum이 null이거나 빈 리스트일 경우 처리
		    count = 0;
		}
		List<Comment> comments=template.query(commentsSQL,new Object[] {p_unique,count},commetRowMapper);
		template.update(UdateSQL,p_unique);
		Post.put("commentnum", commentnum);
		Post.put("comments", comments);
		Post.put("onePost", onePost);
		
	    return Post;
	}

	@Override
	public Map<String,Object> getAllPosts(int ps) {
		RowMapper<Post> postRowMapper=new PostRowMapper();
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
		RowMapper<Post> postRowMapper=new PostRowMapper();
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
	public void updatePost(Post post) {
		String SQL="UPDATE post SET title = ?, contents = ?, isPrivate= ?, region=?, satisfaction=? WHERE id=?";
		template.update(SQL,post.getTitle(),post.getContents(),post.getIsPrivate(),post.getRegion(),post.getSatisfaction(),post.getId());

	}


	@Override
	public void deletePost(int p_unique) {
		String SQL="delete from post where p_unique=?";
		template.update(SQL,p_unique);
		
	}

	
	
	
}