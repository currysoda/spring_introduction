package com.hello.hello_spring.repository;

// import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;

import com.hello.hello_spring.domain.Member;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {
	
	MemberRepository repository = new MemoryMemberRepository();
	
	@BeforeEach
	void setUp() {
	}
	
	@AfterEach
	void tearDown() {
		repository.clearStore();
	}
	
	// 테스트 코드 예제
	@Test
	void save() {
		// given
		Member member = new Member();
		member.setName("spring1");
		
		// when
		repository.save(member);
		
		// then
		Member result = repository.findById(member.getId()).orElseThrow();
		
		// assertEquals 예제
		//		assertEquals(member.getId(), result.getId()); // 성공하는 테스트
		//		assertEquals(member.getId(), null); // 실패하는 테스트
		
		// assertThat 예제
		// assertThat 은 assertJ 패키지를 사용한다.
		assertThat(member.getId()).isEqualTo(result.getId()); // 성공하는 테스트
	}
	
	@Test
	void findById() {
	
	}
	
	@Test
	void findByName() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		Member result = repository.findByName("spring1").orElseThrow();
		
		assertThat(member1.getName()).isEqualTo(result.getName());
	}
	
	@Test
	void findAll() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		List<Member> result = repository.findAll();
		
		assertThat(result.size()).isEqualTo(2);
	}
}