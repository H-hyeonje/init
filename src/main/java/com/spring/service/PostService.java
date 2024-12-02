package com.spring.service;

import java.util.List;

import com.spring.domain.Post;

public interface PostService {
	public void PostSave(Post post);
	public Post PostRead(String title,int p_unique);
	public List<Post> AllRead();
}



