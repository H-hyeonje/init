package com.spring.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.dao.*;
import com.spring.domain.Post;

@Service
public class PostServiceImpl implements PostService{
	@Autowired
	PostRepository repository;
	
	@Override
	public void savePost(Post post) {
		System.out.println(post.getPublishDate());
		repository.savePost(post);
	}

	@Override
	public Map<String,Object> getPost(int p_unique) {
		Map<String,Object> onePost=repository.getPost(p_unique);//나중에 공개여부 boolean값 추가
		return onePost;
	}

	@Override
	public Map<String,Object> getAllPosts(int ps) {
		Map<String,Object> result =repository.getAllPosts(ps);
		return result;
	}

	@Override
	public Map<String, Object> getUserPosts(String id,int ps) {
		Map<String, Object> result=repository.getUserPosts(id,ps);
		return result;
	}

	@Override
	public int updatePost(Post post) {
		int unique=repository.updatePost(post);
		return unique;
	}

	@Override
	public void deletePost(int p_unique) {
		repository.deletePost(p_unique);	
	}

}
