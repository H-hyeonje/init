package com.spring.service;

import java.util.List;
import java.util.Map;

import com.spring.domain.Post;

public interface PostService {
	public void PostSave(Post post);
	public Post PostRead(int p_unique);
	public Map<String,Object> AllRead(int ps);
	public Map<String, Object> getBoard(String id,int ps);
	public int PostUpdate(Post post);
	public void PostDelete(int p_unique);
}



