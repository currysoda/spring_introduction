package com.hello.hello_spring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.hello.hello_spring.domain.Member;
import org.assertj.core.api.AbstractThrowableAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {
	
	MemberService memberService;
	
	// 각각의 테스트가 실행될때 독립적으로 생성될 수 있도록 함
	@BeforeEach
	void setUp() {
		memberService = new MemberService();
		memberService.clearAll();
	}
	
	// 테스트 코드 작성은 메소드 이름을 한글로 작성할 수 있다.
	@Test
	void 회원가입() {
		// given
		Member member = new Member();
		member.setName("hello");
		
		// when
		Long saveId = memberService.join(member);
		
		// then
		assertThat(saveId).isEqualTo(memberService.findOne(saveId).orElseThrow().getId());
	}
	
	@Test
	public void 중복_회원_예외() throws Exception {
		// given
		Member member1 = new Member();
		member1.setName("spring1");
		
		Member member2 = new Member();
		member2.setName("spring1");
		
		// when
		memberService.join(member1);
		assertThatThrownBy(() -> {
			memberService.join(member2);
		})
			.isInstanceOf(IllegalStateException.class)
			.hasMessage("이미 존재하는 회원입니다.");
		
		// then
		
	}
	
	@Test
	void findMembers() {
	}
	
	@Test
	void findOne() {
	}
}