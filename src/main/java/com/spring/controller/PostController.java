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

import com.spring.domain.*;

import com.spring.service.PostService;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class PostController {
	SimpleDateFormat daysFormat=new SimpleDateFormat("yy-MM-dd");
	SimpleDateFormat timesFormat =new SimpleDateFormat("HH:mm");
	
	
	private ArrayList<Integer> calculateTotalPages(int totalItems) {
		ArrayList<Integer> totalPages =new ArrayList<Integer>();
		int itemsPerPage =5;
		int page = (int) Math.ceil((double) totalItems/ itemsPerPage ); 
		while(page>0) {
			totalPages .add(page);
			page-=1;
			
		}
		return totalPages ;	
	}
	private ArrayList<Integer> calculatePageNumbers(int totalPages) {
		ArrayList<Integer> pageNumbers=new ArrayList<Integer>();
		while (totalPages>0) {
			pageNumbers.add(totalPages);
			totalPages-=1;
		}
		
		return pageNumbers;
	}
	
	@Autowired
	PostService postService;
	
	@GetMapping("/")
	public String indax() {
		
		return "index";
	}
	
	@GetMapping("/Post")
	public String postForm() {
		
		return "PostForm";
	}
	
	@PostMapping("/Postadd")
	public String addPost(@ModelAttribute Post post) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		post.setPublishDate(timestamp);
		postService.savePost(post);
		return "redirect:/PostAllBoard/1";
	}
	
	@GetMapping("/Postview/{p_unique}")
	public String Postviews(@PathVariable int p_unique,Model model) {
		Map<String, Object> onePost =postService.getPost(p_unique);//공개 여부 추가 boolean
		Post post=(Post) onePost.get("onePost");
		List<Comment> comments=(List<Comment>)onePost.get("comments");
		
		String publishDate=daysFormat .format(post.getPublishDate());
		model.addAttribute("comments",comments);
		model.addAttribute("publishDate",publishDate);
		model.addAttribute("Post",post);
		return "Postview";
	}

	
	
	@GetMapping("/PostAllBoard/{ps}")
	public String viewAllPosts(@PathVariable int ps, Model model) {
		Map<String, Object> result = postService.getAllPosts(ps);
		List<String> formattedDates = new ArrayList<String>();
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		List<Post> allPosts=(List<Post>)result.get("posts");
		int totalPages =(Integer)result.get("totalPages");
		long midnightCalendar =calendar.getTimeInMillis();
		
		for(Post post:allPosts) {

			 if (post.getPublishDate().getTime()>=midnightCalendar ) {
				 String times=timesFormat .format(post.getPublishDate());
				 formattedDates.add(times);   
			 }
			 else {
				 String days=daysFormat .format(post.getPublishDate());
				 formattedDates.add(days); 
			 }
			 }
		//덧글 
		
	
		
		ArrayList<Integer> pageNumbers=calculateTotalPages(totalPages);
		ArrayList<Integer> totalPagesList=calculatePageNumbers(totalPages);
		Collections.reverse(pageNumbers);
		model.addAttribute("pageNumbers",pageNumbers);
		model.addAttribute("totalPagesList", totalPagesList);
		model.addAttribute("formattedDates",formattedDates);
		model.addAttribute("allPosts",allPosts);
		return "PostAllBoard";
	}
	
	@GetMapping("/PostBoard/{ps}")
	public String postBoard(@RequestParam String id,@PathVariable int ps, Model model) {
		List<String> formattedBoardTimes= new ArrayList<String>();
		Map<String, Object> result=postService.getUserPosts(id,ps);
		List<Post> userPosts =(List<Post>) result.get("Board");
		int currentPageNumber =(int) result.get("pagenum");
		for(Post post : userPosts) {
			String timeDate=daysFormat .format(post.getPublishDate());
			formattedBoardTimes.add(timeDate);}
		
		
		ArrayList<Integer> totalPageNumbers =calculateTotalPages(currentPageNumber);
		ArrayList<Integer> postPageNumbers=calculatePageNumbers(currentPageNumber);
		Collections.reverse(totalPageNumbers);
		model.addAttribute("postPageNumbers",postPageNumbers);
		model.addAttribute("totalPageNumbers",totalPageNumbers);
		model.addAttribute("formattedBoardTimes",formattedBoardTimes);
		model.addAttribute("userId",userPosts);
		return "PostBoard";
	}
	
	@GetMapping("/PostupdatePage/{id}/{p_unique}")
	public String getMethodName(@PathVariable String id,@PathVariable int p_unique, Model model) {
		//id 세션이랑 맞는지 확인하는거 추가
		Map<String,Object> Post=postService.getPost(p_unique);
		Post postToEdit=(Post) Post.get("onePost");
		String privacyStatus=null;
		if(postToEdit.getIsPrivate()==true) {
			privacyStatus="공개";
		}else {
			privacyStatus="비공개";

		}
		
		model.addAttribute("postToEdit", postToEdit);
		model.addAttribute("privacyStatus", privacyStatus);
		return "PostupdatePage";
	}
	
	@PostMapping("/PostUpdate")
	public String PostUpdate(@ModelAttribute Post updatedPost) {
		postService.updatePost(updatedPost);
		System.out.println(updatedPost.getP_unique());
		return "redirect:/Postview/"+updatedPost.getP_unique();
	}
	
	@GetMapping("/PostDelete/{id}/{p_unique}")
	private String PostDelete(@PathVariable String id,@PathVariable int p_unique) {
		postService.deletePost(p_unique);
		
		return "redirect:/PostAllBoard/1";
	}
	
	
	@GetMapping("/editor")
	public String getMethodName() {
		return "editor";
	}
	
	
}
