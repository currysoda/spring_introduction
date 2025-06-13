package com.hello.hello_spring.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.context.annotation.EnableMBeanExport;

// 회원 클래스 예제
@Entity
public class Member {
	
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 값을 자동으로 생성함 (strategy = 값 생성 전략 IDENTITY 는 DB 자동생성)
	private Long   Id;
	@Column(name = "name") // attribute 이름을 정해줌
	private String name;
	
	public Long getId() {
		return Id;
	}
	
	public void setId(Long id) {
		Id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
