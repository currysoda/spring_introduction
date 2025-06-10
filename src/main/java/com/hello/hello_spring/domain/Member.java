package com.hello.hello_spring.domain;

// 회원 클래스 예제
public class Member {
	
	private Long Id;
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
