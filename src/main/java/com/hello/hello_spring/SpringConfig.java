package com.hello.hello_spring;

import com.hello.hello_spring.repository.JdbcMemberRepository;
import com.hello.hello_spring.repository.JdbcTemplateMemberRepository;
import com.hello.hello_spring.repository.JpaMemberRepository;
import com.hello.hello_spring.repository.MemberRepository;
import com.hello.hello_spring.repository.MemoryMemberRepository;
import com.hello.hello_spring.service.MemberService;
import jakarta.persistence.EntityManager;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 자바 코드로 직접 스프링 빈 등록하기
@Configuration
public class SpringConfig {
	
	private final MemberRepository memberRepository;
	
	@Autowired
	public SpringConfig(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	// jdbc 개발에 필요한 datasource
	//	private final DataSource dataSource;
	//
	//	public SpringConfig(DataSource dataSource) {
	//		this.dataSource = dataSource;
	//	}
	
	// JPA 는 스프링 컨테이너로부터 EntityManager 가 필요하다
	//	private final EntityManager em;
	//
	//	@Autowired
	//	public SpringConfig(EntityManager em) {
	//		this.em = em;
	//	}
	
	// @Bean 은 스프링 빈 임을 명시한다.
	// @Bean 에서만 인스턴스를 생성하고 생성한 인스턴스는 스프링이 관리한다.
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository);
	}
	
	//	@Bean
	//	public MemberRepository memberRepository() {
	//		// main memory 에 저장
	//		//		return new MemoryMemberRepository();
	//		// jdbc 에 저장
	//		//		return new JdbcMemberRepository(dataSource);
	//		// spring jdbc template 사용
	//		//		return new JdbcTemplateMemberRepository(dataSource);
	//		//		return new JpaMemberRepository(em);
	//	}
}
