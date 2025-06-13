package com.hello.hello_spring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.hello.hello_spring.domain.Member;
import com.hello.hello_spring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional // test 를 계속 실행할 수 있도록 test data 를 지워준다.
public class MemberServiceIntegrationTest {
	
	@Autowired
	MemberService    memberService;
	@Autowired
	MemberRepository memberRepository;
	
	@Test
		//	@Commit // transactional 이 있어도 DB 에 데이터를 반영함
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
}
