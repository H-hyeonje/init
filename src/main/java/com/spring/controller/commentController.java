package com.spring.controller;



import com.spring.domain.Comment;
import com.spring.service.CommentService;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class commentController {
	SimpleDateFormat daysFormat=new SimpleDateFormat("yy.MM.dd.HH:mm");
	SimpleDateFormat timesFormat=new SimpleDateFormat("HH:mm");
	zerotime zero =new zerotime();
	page Page=new page();
	
	
	@Autowired
	CommentService commentService;
	
	
	@PostMapping(value = "/addComment", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public void addComment(@RequestBody Comment comment) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		comment.setCommentDate(timestamp);
		
		commentService.addComment(comment);
	
	}
	
	
	@GetMapping(value = "/readComment", produces = "application/json")
	@ResponseBody
	public Map<String, Object> getCommentList(@RequestParam int p_unique, @RequestParam int page) {
	    ArrayList<String> formattedDates=new ArrayList<String>();
	    Map<String, Object> result = commentService.getcommentList(p_unique, page);
	    
	    List<Comment> comment=(List<Comment>)result.get("commnets");
	    long midnightCalendar=zero.zerotime();
	    for(int i=0;i<comment.size();i++) {
	    	if(comment.get(i).getCommentDate().getTime()>=midnightCalendar) {
	    		 String times=timesFormat .format(comment.get(i).getCommentDate());
				 formattedDates.add(times);   
			 }
			 else {
				 String days=daysFormat .format(comment.get(i).getCommentDate());
				 formattedDates.add(days); 
			 }
	    	
	}
	   
	    
	    List<Integer> pagenum=Page.calculateTotalPages((int) result.get("pagenum"));
	    System.out.println("왔냐"+pagenum.getLast());
	    int tol = 0;
	    if (pagenum != null && !pagenum.isEmpty()) {  // totalPage가 null이 아니고 비어있지 않은지 확인
	        tol = pagenum.getLast();  // 정상적으로 getLast() 호출
	    } else {
	        // totalPage가 null이거나 비어있을 경우 기본값 설정
	        tol = 0;
	    }
	    result.put("tol", tol);
	    result.put("pagenum", pagenum);
	    result.put("comment", comment);
	    result.put("formattedDates", formattedDates);
	    
		return result;

	
	
}
}