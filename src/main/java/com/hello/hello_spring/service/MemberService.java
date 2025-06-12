package com.hello.hello_spring.service;

import com.hello.hello_spring.domain.Member;
import com.hello.hello_spring.repository.MemberRepository;
import com.hello.hello_spring.repository.MemoryMemberRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	/**
	 * 회원 가입
	 */
	public Long join(Member member) {
		// 같은 이름의 중복 회원은 안된다.
		validateDuplicateMember(member);
		
		memberRepository.save(member);
		return member.getId();
	}
	
	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName())
		                .ifPresent(m -> {
			                throw new IllegalStateException("이미 존재하는 회원입니다.");
		                });
	}
	
	/**
	 * 전체 회원 조회
	 */
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}
	
	/**
	 * 회원 하나 조회
	 */
	public Optional<Member> findOne(Long memberId) {
		return memberRepository.findById(memberId);
	}
	
	/**
	 * 저장소 초기화
	 */
	public void clearAll() {
		memberRepository.clearStore();
	}
}
