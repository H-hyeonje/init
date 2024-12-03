package com.spring.controller;



import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.spring.domain.Post;
import com.spring.service.PostService;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class PostController {
	SimpleDateFormat Days=new SimpleDateFormat("yy-MM-dd");
	SimpleDateFormat Times=new SimpleDateFormat("HH:mm");
	List<String> timeList = new ArrayList<String>();
	@Autowired
	PostService postService;
	
	@GetMapping("/")
	public String indax() {
		
		return "index";
	}
	
	@GetMapping("/Post")
	public String Posts() {
		
		return "PostForm";
	}
	
	@PostMapping("/Postadd")
	public String Postviews(@ModelAttribute Post post ,Model model) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		post.setPublishDate(timestamp);
		System.out.println(post.getPublishDate());
		postService.PostSave(post);
		return "redirect:/PostAllBoard";
	}
	
	@GetMapping("/Postview/{p_unique}")
	public String Postviews(@PathVariable int p_unique,Model model) {
		Post post=postService.PostRead(p_unique);//공개 여부 추가 boolean
		String days=Days.format(post.getPublishDate());
		model.addAttribute("days",days);
		model.addAttribute("Post",post);
		return "Postview";
	}
	
	
	@GetMapping("/PostAllBoard")
	public String PostAllBoard(Model model) {
		List<Post> AllPost= postService.AllRead();
		Timestamp currentTime=new Timestamp(System.currentTimeMillis());
		for(int i=0;i<=AllPost.size()-1;i++) {
			 long time=currentTime.getTime()-AllPost.get(i).getPublishDate().getTime();

			 if (time <= 86400000) {
				 String times=Times.format(AllPost.get(i).getPublishDate());
			     timeList.add(times);   
			 }
			 else {
				 String days=Days.format(AllPost.get(i).getPublishDate());
				 timeList.add(days); 
			 }
			 }
		model.addAttribute("time",timeList);
		model.addAttribute("All",AllPost);
		return "PostAllBoard";
	}
	
	@PostMapping("/PostBoard")
	public String postBoard(@RequestParam String id,Model model) {
		List<Post> board=postService.getBoard(id);
		for(int i=0;i<=board.size()-1;i++) {
			String timeDate=Days.format(board.get(i).getPublishDate());
			timeList.add(timeDate);}
		model.addAttribute("time",timeList);
		model.addAttribute("id",board);
		return "PostBoard";
	}
	
	@GetMapping("/Postupdate/{Post.id}/${post.p_unique}")
	public String getMethodName(@PathVariable String id,@PathVariable int p_unique, Model model) {
		//id 세션이랑 맞는지 확인하는거 추가
		
		
		return "Postupdate";
	}
	
	
	
	
}
