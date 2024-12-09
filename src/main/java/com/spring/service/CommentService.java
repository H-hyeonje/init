package com.spring.service;


import java.util.Map;

import com.spring.domain.Comment;

public interface CommentService {
  public void addComment(Comment comment);
  public Map<String, Object> getcommentList(int P_unique,int page);
}
