package com.hello.hello_spring.controller;

import com.hello.hello_spring.dto.Hello;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
	@GetMapping("/hello")
	public String hello(Model model) {
		model.addAttribute("data", "hello!! spring");
		
		return "hello";
	}
	
	// required = true(default)
	@GetMapping("/hello-mvc")
	public String helloMvc(@RequestParam(value = "name", required = true) String name, Model model) {
		model.addAttribute("name", name);
		
		return "hello-template";
	}
	
	// @ResponseBody 의 사용 문자열이 그대로 반환된다.
	@GetMapping("/hello-String")
	@ResponseBody
	public String helloString(@RequestParam(value = "name") String name) {
		return "hello " + name;
	}
	
	// @ResponseBody 의 반환값을 객체로 하면 자동으로 JSON 을 만들어서 반환한다.
	@GetMapping("/hello-api")
	@ResponseBody
	public Hello helloApi(@RequestParam("name") String name) {
		Hello hello = new Hello();
		hello.setName(name);
		return hello;
	}
}
