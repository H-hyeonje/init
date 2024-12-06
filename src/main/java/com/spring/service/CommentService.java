package com.spring.service;

import java.util.List;

import com.spring.domain.Comment;

public interface CommentService {
  public void addComment(Comment comment);
  public List<Comment> getCommentsByPostId(int P_unique);
}
