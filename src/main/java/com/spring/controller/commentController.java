package com.spring.controller;



import com.spring.domain.Comment;
import com.spring.service.CommentService;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class commentController {
	SimpleDateFormat daysFormat=new SimpleDateFormat("yyyy.MM.dd.HH:mm");
	SimpleDateFormat timesFormat=new SimpleDateFormat("HH:mm");
	
	@Autowired
	CommentService commentService;
	@PostMapping(value = "/addComment", consumes = "application/json", produces = "application/json")
	public Map<String,Object> addComment(@RequestBody Comment comment) {
		Map<String,Object> Comment=new HashMap<String,Object>();
	    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		List<String> formattedDates = new ArrayList<String>();
	    comment.setCommentDate(timestamp);
	    commentService.addComment(comment);
	    List<Comment> comments = commentService.getCommentsByPostId(comment.getP_unique());
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		long midnightCalendar =calendar.getTimeInMillis();
	    System.out.println(comments.get(0).getCommentDate());
	    
	    for(int i=1;i<comments.size();i++) {
			 if (comments.get(0).getCommentDate().getTime()>=midnightCalendar ) {
				 String times=timesFormat .format(comments.get(0).getCommentDate());
				 formattedDates.add(times);   
			 }
			 else {
				 String days=daysFormat .format(comments.get(0).getCommentDate());
				 formattedDates.add(days); 
			 }
	    	
	    }
	    Comment.put("comments", comments);
	    Comment.put("formattedDates", formattedDates);
	    
	    
	    return Comment; 
	}
	
	
	
	
}
