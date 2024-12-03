package com.spring.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.domain.Member;

public class MemberMapper implements RowMapper<Member>
{
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		Member member = new Member();
		member.setId(rs.getString(1));
		member.setName(rs.getString(2));
		member.setPw(rs.getString(3));
		member.setRegion(rs.getString(4));
		member.setSex(rs.getString(5));
		member.setPhone1(rs.getString(6));
		member.setPhone2(rs.getString(7));
		member.setPhone3(rs.getString(8));
		member.setBirthday(rs.getString(9));

		return member;
	}
}