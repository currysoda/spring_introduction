package com.hello.hello_spring;

import com.hello.hello_spring.repository.MemberRepository;
import com.hello.hello_spring.repository.MemoryMemberRepository;
import com.hello.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 자바 코드로 직접 스프링 빈 등록하기
@Configuration
public class SpringConfig {
	
	// @Bean 은 스프링 빈 임을 명시한다.
	// @Bean 에서만 인스턴스를 생성하고 생성한 인스턴스는 스프링이 관리한다.
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	
	@Bean
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}
}
