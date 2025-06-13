package com.hello.hello_spring.repository;

import com.hello.hello_spring.domain.Member;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


public class JpaMemberRepository implements MemberRepository {
	
	// JPA 는 EntityManager 를 통해 실행된다.
	private final EntityManager em;
	
	// EntityManager 는 스프링 컨테이너로부터 주입받는다.
	@Autowired
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Member save(Member member) {
		em.persist(member);
		return member;
	}
	
	@Override
	public Optional<Member> findById(Long id) {
		Member member = em.find(Member.class, id);
		return Optional.ofNullable(member);
	}
	
	@Override
	public Optional<Member> findByName(String name) {
		List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
		                        .setParameter("name", name)
		                        .getResultList();
		return result.stream().findAny();
	}
	
	@Override
	public List<Member> findAll() {
		return em.createQuery("select m from Member as m", Member.class)
		         .getResultList();
	}
	
	@Override
	public void clearStore() {
	
	}
}
