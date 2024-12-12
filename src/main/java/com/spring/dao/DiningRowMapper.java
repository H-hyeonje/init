package com.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.domain.Dining;


public class DiningRowMapper implements RowMapper<Dining> {

	@Override
	public Dining mapRow(ResultSet rs, int rowNum) throws SQLException {
		Dining dining=new Dining();
		dining.setName(rs.getString(1));
		dining.setAddress(rs.getString(2));
		dining.setD_unique(rs.getInt(3));
		dining.setLatitude(rs.getDouble(4));
		dining.setLongitude(rs.getDouble(5));
		dining.setPhone1(rs.getString(6));
		dining.setMenu(rs.getString(7));
		return dining;
		
	}

}
