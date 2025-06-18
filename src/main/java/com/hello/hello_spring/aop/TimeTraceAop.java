package com.hello.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// spring bean 으로 등록해야한다.
@Component
// AOP 클래스 임을 @Aspect 를 붙여서 명시해야한다.
@Aspect
public class TimeTraceAop {
	
	@Around("execution(* com.hello.hello_spring..*(..))")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		
		long start = System.currentTimeMillis();
		
		System.out.println("START = " + joinPoint.toString());
		
		try {
			return joinPoint.proceed();
		}
		finally {
			long finish = System.currentTimeMillis();
			long timeMs = finish - start;
			System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms");
		}
		
	}
}
