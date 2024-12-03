package com.spring.dao;

import java.util.List;

import com.spring.domain.Post;

public interface PostRepository {
	public void PostSave(Post post);
	public Post PostRead(int p_unique);
	public List<Post> AllRead();
	public List<Post> getBoard(String id);
}
