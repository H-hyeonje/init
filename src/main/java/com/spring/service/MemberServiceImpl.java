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
}