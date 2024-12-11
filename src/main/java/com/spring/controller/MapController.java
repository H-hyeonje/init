package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MapController {
	
	
	   @GetMapping("/Map")
	    public String Mapping() {
	        return "Map";  // Map.jsp 또는 map.html이 정상적으로 반환되는지 확인
	    }
	}

