package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.domain.Member;
import com.spring.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService
{
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public void createMember(Member member) 
	{
		memberRepository.createMember(member);
	}

	@Override
	public Member findById(String id) 
	{
		return memberRepository.findById(id);
	}

	@Override
	public void updateMember(Member member) 
	{
		memberRepository.updateMember(member);
	}

	@Override
	public void deleteMember(Member member) 
	{
		memberRepository.deleteMember(member);
	}

	@Override
	public List<Member> readAllMember() 
	{
		return memberRepository.readAllMember();
	}
	
	@Override
	public List<Member> searchMember(String name) 
	{
	    return memberRepository.searchMember(name);
	}

	
	@Override
	public boolean checkUp(String field, String value) 
	{
		if (!List.of("id", "password", "email", "phone").contains(field)) { // 허용된 필드만 확인 (SQL Injection 방지)
            throw new IllegalArgumentException("Invalid field: " + field);
        }
        return memberRepository.checkUp(field, value);
	}
}
