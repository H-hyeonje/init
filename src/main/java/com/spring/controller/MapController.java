package com.spring.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.service.mapService;
import com.spring.domain.*;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class mapController {
	@Autowired
	mapService mapService;

	@GetMapping("/Map")
	public String Maps() {
		return "Map";
	}
	
	@GetMapping(value = "/Maps", produces = "application/json")
	@ResponseBody
	public List<Dining> getAllDiningList() {
	    return mapService.getAllDining();
	}
	
	
	@GetMapping(value="/Maps/search",produces = "application/json")
	@ResponseBody
	public List<Dining> getDiningList(@RequestParam(required = false) String search){
		
		
		return mapService.getDiningList(search);
	}
	
	
	
}
