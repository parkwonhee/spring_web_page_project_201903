package ex.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

//@Pointcut("execution(public void get*(..))")	// public void인 모든 get메소드
//@Pointcut("execution(* com.javalec.ex.*.*())")	// com.javalec.ex 패키지에 파라미터가 없는 모든 메소드
//@Pointcut("execution(* com.javalec.ex..*.*())")	// com.javalec.ex 패키지 & com.javalec.ex 하위 패키지에 파라미터가 없는 모든 메소드
//@Pointcut("execution(* com.javalec.ex.Worker.*())")	// com.javalec.ex.Worker 안의 모든 메소드

//@Pointcut("within(com.javalec.ex.*)")	//com.javalec.ex 패키지 안에 있는 모든 메소드
//@Pointcut("within(com.javalec.ex..*)")  //com.javalec.ex 패키지 및 하위 패키지 안에 있는 모든 메소드
//@Pointcut("within(com.javalec.ex.Worker)") //com.javalec.ex.Worker 모든 메소드

//@Pointcut("bean(student)")	//student 빈에만 적용
//@Pointcut("bean(*ker)")		//~ker로 끝나는 빈에만 적용



//공통기능할 프록시
@Aspect
public class LogAop {

	@Pointcut("within(ex.aop.*)")
	private void pointcutMethod() {
	}
	
	//핵심코드 실행 하기전과 후에 한번씩 실행되는것
	@Around("pointcutMethod()")
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable { //advice용
		String signatureStr = joinpoint.getSignature().toShortString();
		System.out.println( signatureStr + " is start.");
		long st = System.currentTimeMillis();
		
		try {
			Object obj = joinpoint.proceed();
			return obj;
		} finally {
			long et = System.currentTimeMillis();
			System.out.println( signatureStr + " is finished.");
			System.out.println( signatureStr + " 경과시간 : " + (et - st));
		}
		
	}
	
	//reference안하고 beforAdvice가 핵심기능을 실행하기전에 된
	@Before("within(ex.aop.*)") 
	public void beforAdvice() {
		System.out.println("beforAdvice()");
	}

}
