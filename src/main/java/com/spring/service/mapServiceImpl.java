package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.mapRepository;
import com.spring.domain.Dining;

@Service
public class mapServiceImpl implements mapService {
	@Autowired
	mapRepository mapRepository;
	
	@Override
	public List<Dining> getAllDining() {
		List<Dining> result=mapRepository.getAllDining();
		return result;
	}

	@Override
	public List<Dining> getDiningList(String search) {
		
		return mapRepository.getDiningList(search);
	}

}
