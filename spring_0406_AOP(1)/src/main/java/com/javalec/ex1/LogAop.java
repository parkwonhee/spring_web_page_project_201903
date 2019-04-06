package com.javalec.ex1;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogAop {
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable {
		String signatureStr = joinpoint.getSignature().toShortString();
		System.out.println( signatureStr + " is start.");
		long st = System.currentTimeMillis();//시작부여
		
		try {
			Object obj = joinpoint.proceed();//대행자, 그리고 핵심코드 실행시켜줌
			return obj;
		} finally {
			long et = System.currentTimeMillis();//핵심기능 끝난후의 시간을 구해줌
			System.out.println( signatureStr + " is finished.");
			System.out.println( signatureStr + " 경과시간 : " + (et - st));//실제 핵심코드 로직이 돌아간 시각을 구하자
		}
		
	}
}
