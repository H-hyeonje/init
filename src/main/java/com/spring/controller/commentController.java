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
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class commentController {
	SimpleDateFormat daysFormat=new SimpleDateFormat("yy.MM.dd.HH:mm");
	SimpleDateFormat timesFormat=new SimpleDateFormat("HH:mm");
	zerotime zero =new zerotime();
	@Autowired
	CommentService commentService;
	
	
	@PostMapping(value = "/addComment", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Map<String,Object> addComment(@RequestBody Comment comment) {
		Map<String,Object> Comment=new HashMap<String,Object>();
	    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		List<String> formattedDates = new ArrayList<String>();
	    comment.setCommentDate(timestamp);
	    commentService.addComment(comment);
	    List<Comment> comments = commentService.getCommentsByPostId(comment.getP_unique());

		long midnightCalendar =zero.zerotime();
	    System.out.println(comments.get(0).getCommentDate());
	    
	    for(int i=1;i<comments.size();i++) {
			 if (comments.get(i).getCommentDate().getTime()>=midnightCalendar ) {
				 String times=timesFormat .format(comments.get(i).getCommentDate());
				 formattedDates.add(times);   
			 }
			 else {
				 String days=daysFormat .format(comments.get(i).getCommentDate());
				 formattedDates.add(days); 
			 }
	    	
	    }
	    Comment.put("comments", comments);
	    Comment.put("formattedDates", formattedDates);
	    
	    
	    return Comment; 
	}
	

	
	
}
