package com.spring.dao;


import java.util.Map;

import com.spring.domain.Post;

public interface PostRepository {
	public void savePost(Post post);
	public Map<String,Object> getPost(int p_unique);
	public Map<String,Object> getAllPosts(int ps);
	public Map<String,Object> getUserPosts(String id,int ps);
	public int updatePost(Post post);
	public void deletePost(int p_unique);
}
