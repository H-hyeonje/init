package com.spring.service;

import java.util.List;
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
	public void PostSave(Post post) {
		System.out.println(post.getPublishDate());
		repository.PostSave(post);
	}

	@Override
	public Post PostRead(int p_unique) {
		Post onePost=repository.PostRead(p_unique);//나중에 공개여부 boolean값 추가
		return onePost;
	}

	@Override
	public Map<String,Object> AllRead(int ps) {
		Map<String,Object> result =repository.AllRead(ps);
		return result;
	}

	@Override
	public Map<String, Object> getBoard(String id,int ps) {
		Map<String, Object> result=repository.getBoard(id,ps);
		return result;
	}

	@Override
	public int PostUpdate(Post post) {
		int unique=repository.PostUpdate(post);
		return unique;
	}

	@Override
	public void PostDelete(int p_unique) {
		repository.PostDelete(p_unique);	
	}

}
