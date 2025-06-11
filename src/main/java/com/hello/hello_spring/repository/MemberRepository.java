package com.hello.hello_spring.repository;

import com.hello.hello_spring.domain.Member;
import java.util.List;
import java.util.Optional;

// Member 를 저장하는 저장소를 만듬
// Member 저장소가 갖춰야 하는 기능을 인터페이스로 만들어서 다형성을 이용해 구현체가 달라져도 사용 가능하게 만듬
public interface MemberRepository {
	
	Member save(Member member);
	
	Optional<Member> findById(Long id);
	
	Optional<Member> findByName(String name);
	
	List<Member> findAll();
	
	void clearStore();
}
