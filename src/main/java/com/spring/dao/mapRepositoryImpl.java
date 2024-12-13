package com.spring.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.domain.Dining;

@Repository
public class mapRepositoryImpl implements mapRepository{
	
	@Autowired
	JdbcTemplate template;
	
	
	@Override
	public List<Dining> getAllDining() {
		DiningRowMapper diningRowMapper=new DiningRowMapper();
		List<Dining> result=new ArrayList<Dining>();
		String AllDiningSQL="select * from Dining ";
		result=template.query(AllDiningSQL, diningRowMapper);
		System.out.println(result.size());
		return result;
	}


	@Override
	public List<Dining> getDiningList(String search) {
		DiningRowMapper diningRowMapper=new DiningRowMapper();
		String sql = "SELECT * FROM Dining WHERE name LIKE ? OR address LIKE ? OR menu LIKE ?";
	    return template.query(sql,diningRowMapper, "%" + search + "%","%" + search + "%","%" + search + "%"
	    );
	}
	}


