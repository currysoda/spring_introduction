package com.hello.hello_spring.repository;

import com.hello.hello_spring.domain.Member;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// spring data jpa 를 이용한 예제 인터페이스만 만들어도 자동으로 구현체를 만들어준다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
	
	// 기본적인 메소드는 만들지 않아도 자동으로 만든다.
	
	// JPQL select m from Member m where m.name = ? 쿼리를 자동으로 만들어준다.
	@Override
	Optional<Member> findByName(String name);
}
