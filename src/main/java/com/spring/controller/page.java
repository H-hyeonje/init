package com.spring.controller;

import java.util.ArrayList;
import java.util.Collections;

public class page {
	public ArrayList<Integer> calculateTotalPages(int totalItems) {
		ArrayList<Integer> totalPages =new ArrayList<Integer>();
		int itemsview =5;
		int page = (int) Math.ceil((double) totalItems/ itemsview ); 
		while(page>0) {
			totalPages .add(page);
			page-=1;
			
		}
		Collections.reverse(totalPages);
		return totalPages;	
	}
	public ArrayList<Integer> calculatePageNumbers(int totalPages) {
		ArrayList<Integer> pageNumbers=new ArrayList<Integer>();
		while (totalPages>0) {
			pageNumbers.add(totalPages);
			totalPages-=1;
		}
		
		return pageNumbers;
	}
}
