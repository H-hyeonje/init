package com.spring.dao;

import java.util.List;

import com.spring.domain.Dining;

public interface mapRepository {
	public List<Dining> getAllDining();
	public List<Dining> getDiningList(String search);
}
