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
		System.out.println(post.getPublishDate());
		repository.PostSave(post);
	}

	@Override
	public Post PostRead(int p_unique) {
		Post onePost=repository.PostRead(p_unique);//나중에 공개여부 boolean값 추가
		return onePost;
	}

	@Override
	public List<Post> AllRead() {
		List<Post> AllPost=repository.AllRead();
		return AllPost;
	}

	@Override
	public List<Post> getBoard(String id) {
		List<Post> board=repository.getBoard(id);
		return board;
	}

}
