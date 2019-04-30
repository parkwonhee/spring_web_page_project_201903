AOP
----
#java파일에서 구현 방법

AOP방법은 핵심 기능과 공통 기능을 분리 시켜놓고, 공통 기능을 필요로 하는 핵심 기능들에서 사용하는 방식 이다.
공통기능은 바뀌지 않는다.

 - Aspect : 공통 기능

 - Advice : Aspect의 기능 자체  //핵심자체

 - Jointpoint : Advice를 적용해야 되는 부분( ex, 필드, 메소드 ) (스프링에서는 메소드만 해당) /핵심기능 하나하나

 - Pointcut : Jointpoint의 부분으로 실제로 Advice가 적용된 부분

 - Weaving : Advice를 핵심 기능에 적용 하는 행위 자체

스프링에서 AOP 구현 방법 : proxy를 이용 한다.(대행)



@Aspect를 이용한 AOP구현

작업 순서 (spring_10_1_ex1_springex)

 1) 의존 설정(pom.xml) 

 2) @Aspect 어노테이션을 이용한 Aspect클래스 제작

 3) XML파일에 <aop:aspectj-autoproxy /> 설정

namespace에서 aop추가

poem.xml추가

```
<!-- AOP -->
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjweaver</artifactId>
			<version>1.7.4</version>
		</dependency>
```

LogAop.java
```
//공통기능할 프록시
@Aspect

//핵심코드 실행 하기전과 후에 한번씩 실행되는것
	@Around("pointcutMethod()")

//reference안하고 beforAdvice가 핵심기능을 실행하기전에 된
	@Before("within(ex.aop.*)") 
```


-----

AspectJ Pointcut 표현식
---

Pointcut을 지정할 때 사용하는 표현식으로 AspectJ 문법을 사용 한다.

* : 모든
.  : 현재
.. : 0개 이상

-Execution
-within
@Before("within(ex.aop.*)") 

LogAop.java 

```
//	@Pointcut("execution(public void get*(..))")	// public void인 모든 get메소드
//	@Pointcut("execution(* com.javalec.ex.*.*())")	// com.javalec.ex 패키지에 파라미터가 없는 모든 메소드
//	@Pointcut("execution(* com.javalec.ex..*.*())")	
// com.javalec.ex 패키지 & com.javalec.ex 하위 패키지에 파라미터가 없는 모든 메소드
//	@Pointcut("execution(* com.javalec.ex.Worker.*())")	// com.javalec.ex.Worker 안의 모든 메소드

//	@Pointcut("within(com.javalec.ex.*)")	//com.javalec.ex 패키지 안에 있는 모든 메소드
//	@Pointcut("within(com.javalec.ex..*)")  //com.javalec.ex 패키지 및 하위 패키지 안에 있는 모든 메소드
//	@Pointcut("within(com.javalec.ex.Worker)") //com.javalec.ex.Worker 모든 메소드
	
//	@Pointcut("bean(student)")	//student 빈에만 적용
	@Pointcut("bean(*ker)")		//~ker로 끝나는 빈에만 적용
```
