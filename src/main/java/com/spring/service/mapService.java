package com.spring.service;
import java.util.List;
import com.spring.domain.*;
public interface mapService {
	public List<Dining> getAllDining();
	public List<Dining> getDiningList(String search);
}
