package com.spring.controller;



import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
	List<String> timeList;
	
	private ArrayList<Integer> totalPage(int totalitems) {
		ArrayList<Integer> total=new ArrayList<Integer>();
		int i=5;
		int page = (int) Math.ceil((double) totalitems/ i); 
		while(page>0) {
			total.add(page);
			page-=1;
			
		}
		return total;	
	}
	private ArrayList<Integer> number(int a) {
		ArrayList<Integer> number=new ArrayList<Integer>();
		while (a>0) {
			number.add(a);
			a-=1;
		}
		
		return number;
	}
	
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
	public String Postviews(@ModelAttribute Post post) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		post.setPublishDate(timestamp);
		System.out.println(post.getPublishDate());
		postService.PostSave(post);
		return "redirect:/PostAllBoard/1";
	}
	
	@GetMapping("/Postview/{p_unique}")
	public String Postviews(@PathVariable int p_unique,Model model) {
		Post post=postService.PostRead(p_unique);//공개 여부 추가 boolean
		String days=Days.format(post.getPublishDate());
		model.addAttribute("days",days);
		model.addAttribute("Post",post);
		return "Postview";
	}

	
	
	@GetMapping("/PostAllBoard/{ps}")
	public String PostAllBoard(@PathVariable int ps, Model model) {
		Map<String, Object> result = postService.AllRead(ps);
		timeList = new ArrayList<String>();
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		List<Post> AllPost=(List<Post>)result.get("AllPost");
		int AllPage=(Integer)result.get("Allpage");
		long MidnightMillis=calendar.getTimeInMillis();
		for(int i=0;i<=AllPost.size()-1;i++) {

			 if (AllPost.get(i).getPublishDate().getTime()>=MidnightMillis) {
				 String times=Times.format(AllPost.get(i).getPublishDate());
			     timeList.add(times);   
			 }
			 else {
				 String days=Days.format(AllPost.get(i).getPublishDate());
				 timeList.add(days); 
			 }
			 }
		ArrayList<Integer> Allnumber=number(AllPage);
		
		
		ArrayList<Integer> total=totalPage(AllPage);
		Collections.reverse(total);
		model.addAttribute("Allnumber",Allnumber);
		model.addAttribute("total", total);
		model.addAttribute("time",timeList);
		model.addAttribute("All",AllPost);
		return "PostAllBoard";
	}
	
	@GetMapping("/PostBoard/{ps}")
	public String postBoard(@RequestParam String id,@PathVariable int ps, Model model) {
		timeList = new ArrayList<String>();
		Map<String, Object> result=postService.getBoard(id,ps);
		List<Post> board =(List<Post>) result.get("Board");
		int pagenum=(int) result.get("pagenum");
		for(int i=0;i<=board.size()-1;i++) {
			String timeDate=Days.format(board.get(i).getPublishDate());
			timeList.add(timeDate);}
		
		
		List<Integer> pages=totalPage(pagenum);
		Collections.reverse(pages);
		model.addAttribute("pagenum",pagenum);
		model.addAttribute("pages",pages);
		model.addAttribute("time",timeList);
		model.addAttribute("id",board);
		return "PostBoard";
	}
	
	@GetMapping("/PostupdatePage/{id}/{p_unique}")
	public String getMethodName(@PathVariable String id,@PathVariable int p_unique, Model model) {
		//id 세션이랑 맞는지 확인하는거 추가
		Post post=postService.PostRead(p_unique);
		String Private=null;
		if(post.getIsPrivate()==true) {
			Private="공개";
		}else {
			Private="비공개";

		}
		
		model.addAttribute("post", post);
		model.addAttribute("Private", Private);
		return "PostupdatePage";
	}
	
	@PostMapping("/PostUpdate")
	public String PostUpdate(@ModelAttribute Post post) {
		postService.PostUpdate(post);
		System.out.println(post.getP_unique());
		return "redirect:/Postview/"+post.getP_unique();
	}
	
	@GetMapping("/PostDelete/{id}/{p_unique}")
	private String PostDelete(@PathVariable String id,@PathVariable int p_unique) {
		postService.PostDelete(p_unique);
		
		return "redirect:/PostAllBoard/1";
	}
	
}
