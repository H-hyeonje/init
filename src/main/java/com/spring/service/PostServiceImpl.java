package com.spring.service;

import java.util.List;

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
		repository.PostSave(post);
	}

	@Override
	public Post PostRead(String title,int p_unique) {
		Post onePost=repository.PostRead(title,p_unique);
		return onePost;
	}

	@Override
	public List<Post> AllRead() {
		List<Post> AllPost=repository.AllRead();
		return AllPost;
	}

}
