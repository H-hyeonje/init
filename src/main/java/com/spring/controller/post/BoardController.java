package com.spring.controller.post;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.domain.Member;
import com.spring.domain.Post;
import com.spring.domain.Tour;
import com.spring.service.post.BoardService;

@Controller
@RequestMapping("/board")
@PropertySource("classpath:properties/API.key.properties") 
public class BoardController 
{
	
	PaginationHelper paginationHelper = new PaginationHelper();
	
	@Autowired
	BoardService boardService;
	
	private void setBoardModelAttributes(Map<String,Object> result,int page,Model model) 
	//게시물을 들고오고 게시물 수를 들고옴과 동시에 페이지네이션 데이터를 생성, 게시물 생성 날짜 포맷팅을 해줌
	{
		List<Post> postList = (List<Post>) result.get("postList"); //모든 게시글을 result에서 들고와서 리스트에 담는다
		int postSize= (int)result.get("postSize"); //게시글 갯수의 총 합을 result에서 들고와서 변수에 담는다
		
		ArrayList<Integer> getTotalPages = paginationHelper.getTotalPages(postSize, 10); //페이지네이션 만드는 코드
		ArrayList<Integer> getpostnumber = paginationHelper.getpostnumber(postSize, page, 10); //
		
		DateFormatter dateFormatter = new DateFormatter(); //게시물의 날짜를 포멧에 맞춰주는 객체
		ArrayList<String> date=new ArrayList<String>(); //
		for (Post post : postList) //Allpost에 있는 요소를 순회하면서 post에 담는다
		{
		       date.add(dateFormatter.formatBoardDate(post.getPublishDate())); 
		}
		 
		model.addAttribute("date", date);
		model.addAttribute("getTotalPages", getTotalPages);
		model.addAttribute("getpostnumber", getpostnumber);
		model.addAttribute("postList", postList);
	}
	
	
	@GetMapping("/all")
	public String allBoard(@RequestParam(value = "page",defaultValue = "1")int page, Model model) 
	{ //페이지 파라미터 받아오기
		System.out.println("===========================================================================================");
        System.out.println("BoardController : board/all(GET)으로 매핑되어 전체 글 목록을 표시할 준비가 되었습니다.");
		Map<String,Object> result = boardService.allBoard(page);
		setBoardModelAttributes(result,page,model);
		int totalPosts = (int) result.get("postSize");
		Map<String, Object> pagination = paginationHelper.getPagination(page, totalPosts, 10, 5);
		
		model.addAttribute("currentPage", page);
		model.addAttribute("pagination", pagination);

//		System.out.println("게시글 날짜 : " + model.getAttribute("date"));
//		System.out.println("총 페이지 갯수 : " + model.getAttribute("getTotalPages"));
//		System.out.println("getPostNumber로 가져온 값 : " + model.getAttribute("getpostnumber"));
//		System.out.println("총 게시글 리스트 : " + model.getAttribute("postList"));
//		System.out.println("페이지 번호 : " + page);
//		System.out.println("페이지네이션 : " + pagination);
		
		return "board/allBoard";
	}
	
	
	@GetMapping("/all/search")
	public String searchBoard(@RequestParam(value = "page", defaultValue = "1") int page,
	                          @RequestParam("type") String type,
	                          @RequestParam("keyword") String keyword,
	                          Model model) 
	{
		System.out.println("===========================================================================================");
        System.out.println("BoardController : board/all/search(GET)으로 매핑되었습니다.");
        
	    Map<String, Object> result = boardService.allBoardSearch(type, keyword, page);
	    System.out.println("db에서 가져온 결과 값" + result);
	    
	    setBoardModelAttributes(result, page, model);
	    int totalPosts = (int) result.get("postSize"); 
	    Map<String, Object> pagination = paginationHelper.getPagination(page, totalPosts, 10, 5);
	    
	    

	 
	    model.addAttribute("type", type);
	    model.addAttribute("keyword", keyword);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("pagination", pagination);
	    return "board/allBoardSearch";
	}
	
	@GetMapping("/hot")
	public String toHotBoard(@RequestParam(value = "page",defaultValue = "1")int page, Model model) 
	{
		System.out.println("===========================================================================================");
        System.out.println("BoardController : board/hot(GET)으로 매핑되었습니다.");
		int size = 10;
		Map<String,Object> result=boardService.hotBoard(size, page); //
		setBoardModelAttributes(result,page,model);
		
		int totalPosts = (int) result.get("postSize");
		Map<String, Object> pagination = paginationHelper.getPagination(page, totalPosts, 10, 5);
		model.addAttribute("currentPage", page);
		model.addAttribute("pagination", pagination);
		return "board/hotBoard";
	}
	
	
	@GetMapping("/myBoard")
	public String toMyBoard(HttpSession session,Model model, @RequestParam(value = "page", defaultValue = "1") int page) 
	{
		System.out.println("===========================================================================================");
        System.out.println("BoardController : board/myBoard(GET)으로 매핑되었습니다.");
		Member member=(Member)session.getAttribute("user");
		if(member != null) 
		{
			Map<String,Object> result=boardService.myBoard(member.getEmail(),page);
			int Allpostgetnum=(int) result.get("postSize");
			
			Map<String, Object> pagination = paginationHelper.getPagination(page, Allpostgetnum, 10, 5);
			setBoardModelAttributes(result,page,model);
			model.addAttribute("pagination",pagination);
			model.addAttribute("currentPage", page);
			return "board/myBoard";
		}
		else 
		{
			return "errorPage";
		}
	}
	
	
	
	@GetMapping("/myBoard/search")
	public String mysearchBoard(@RequestParam(value = "page", defaultValue = "1") int page,
	                          @RequestParam("keyword") String keyword,
	                          HttpSession session,
	                          Model model
	                          )
	{
		System.out.println("===========================================================================================");
        System.out.println("BoardController : board/myBoard/search(GET)으로 매핑되었습니다.");
	    if(session!=null) {
	    Member member=(Member)session.getAttribute("user");
	    String id=member.getEmail();
	    Map<String, Object> result = boardService.myBoardSearch(id,keyword, page);
	    setBoardModelAttributes(result, page, model);
	    int totalPosts = (int) result.get("postSize"); 
	    Map<String, Object> pagination = paginationHelper.getPagination(page, totalPosts, 10, 5);

	    model.addAttribute("keyword", keyword);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("pagination", pagination);
	    return "board/myBoardSearch";
	    }else {
			return "errorPage";
		}
	}
	
	
	@GetMapping("/festival")
	public String toBoardFestival(@RequestParam(value = "page", defaultValue = "1") int page, Model model, HttpSession session) 
	{
		System.out.println("===========================================================================================");
        System.out.println("BoardController : board/festival(GET)으로 매핑되었습니다.");
	    int limit = 12; // 한 페이지당 표시할 관광지 수
	    int offset = (page - 1) * limit;
	    String type = "15";
	    // DB에서 전체 관광지 리스트를 가져옴
	    List<Tour> festivals = boardService.hotSpots(type,limit, offset);
	    System.out.println("축제(15): " + festivals.size() + "개");
	    
	    // 모델에 각 카테고리별 리스트 추가
	    model.addAttribute("member", new Member());
	    model.addAttribute("festivals", festivals);
	    model.addAttribute("totalCount", festivals.size());
	    
	    return "board/boardFestival";
	}
	
	@GetMapping("/tour")
	public String toBoardTour(@RequestParam(value = "page", defaultValue = "1") int page, Model model) 
	{
		System.out.println("===========================================================================================");
        System.out.println("BoardController : board/tour(GET)으로 매핑되었습니다.");
	    int limit = 12; // 한 페이지당 표시할 관광지 수
	    int offset = (page - 1) * limit;
	    String type = "12";
	    // DB에서 전체 관광지 리스트를 가져옴
	    List<Tour> tourSpots = boardService.hotSpots(type,limit, offset);
	    
	    // 모델에 각 카테고리별 리스트 추가
	    model.addAttribute("tourSpots", tourSpots);
	    model.addAttribute("totalCount", tourSpots.size());
	    
	    return "board/boardTour";
	}
	
	@GetMapping("/restaurant")
	public String toBoardRestaurant(@RequestParam(value = "page", defaultValue = "1") int page, Model model) 
	{
		System.out.println("===========================================================================================");
        System.out.println("BoardController : board/restaurant(GET)으로 매핑되었습니다.");
	    int limit = 12; // 한 페이지당 표시할 관광지 수
	    int offset = (page - 1) * limit;
	    String type = "39";
	    // DB에서 전체 관광지 리스트를 가져옴
	    List<Tour> restaurants = boardService.hotSpots(type,limit, offset);
	    
	    // 모델에 각 카테고리별 리스트 추가
	    model.addAttribute("restaurants", restaurants);
	    model.addAttribute("totalCount", restaurants.size());
	    
	    return "board/boardRestaurant";
	}
	
	@Value("${TourAPI.key}")
	private String tourAPIKey;
	
	@GetMapping("/detailedInfo")
	public String toDetailedPage(@RequestParam String contentTypeId, @RequestParam String contentId, Model model) 
	{
		System.out.println("===========================================================================================");
        System.out.println("BoardController : board/detailedInfo(GET)으로 매핑되었습니다.");
		model.addAttribute("tourAPIKey", tourAPIKey);
		model.addAttribute("contenttypeid", contentTypeId);
		model.addAttribute("contentid", contentId);
		System.out.println(model.getAttribute("tourAPIKey"));
		
		
		return "board/detailedPage";
	}
}

