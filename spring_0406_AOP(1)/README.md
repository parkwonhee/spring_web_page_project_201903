 
!AOP(Aspect Oriented Programming)
-------

 프로그래밍을 하다 보면, 공통적인 기능이 많이 발생 한다. 
이러한 공통 기능을 모든 모듈에 적용하기 위한 방법으로 상속을 통한 방법이 있습니다.
상속도 좋은 방법이기는 하지만 몇 가지 문제가 있습니다.
우선 JAVA에서는 다중 상속이 불가하므로 다양한 모듈에 상속기법을 통한 공통 기능 부여는 한계가 있습니다.
그리고, 기능 구현부분에 핵심 기능 코드와 공통 기능 코드가 섞여 있어 효율성이 떨어집니다.

위의 상속을 통한 방법에 한계가 있어 `AOP`가 등장하게 되었습니다.
AOP방법은 핵심 기능과 공통 기능을 분리 시켜놓고, 공통 기능을 필요로 하는 핵심 기능들에서 사용하는 방식 입니다.

쉽게 생각해서 아침에 밥을 짓는다고 생각해 봅니다.
핵심 기능은 쌀을 씻고, 깨끗한 물을 적당히 넣고, 전자밥솥에 내솥을 넣고, 취사 버튼을 누르는 기능들 일 것입니다
공통 기능은 수도 꼭지를 열어 물을 받고, 쌀이 깨끗이 씻겼는지 눈으로 판단하고, 물을 적당한지 판단하는 기능들 일 것입니다. 
이러한 기능이 공통 기능인 것은 밥을 짓는 행동이 아닐 때도 우리는 수도 꼭지를 열고, 눈으로 사물을 보고 적절한 판단을 하기 때문에 공통 기능이라고 하였습니다.
어쨌든, 이렇게 핵심 기능과 공통 기능을 분리해 놓고, 추후에 밥을 짓는 행동 말고 팥을 쑬 때도 핵심 기능은 변화지만, 공통 기능은 다시 적용할 수 있을 것입니다.

#AOP 기법이 바로 이러한 것입니다. 공통 기능을 핵심 기능과 분리해 놓고, 공통 기능 중에서 핵심 기능에 적용하고자 하는 부분에 적용하는 것입니다.


 - Aspect : 공통 기능
 #- Advice : Aspect의 기능 자체 //공통
 - Jointpoint : Advice를 적용해야 되는 부분( ex, 필드, 메소드 ) (스프링에서는 메소드만 해당)--
 - Pointcut : Jointpoint의 부분으로 실제로 Advice가 적용된 부분--부분에 딱 매칭되는 것
 - Weaving : Advice를 핵심 기능에 적용 하는 행위


스프링에서 AOP 구현 방법 : proxy를 이용 합니다.//대행인

 - XML 스키마 기반의  AOP구현

작업 순서
 1) 의존 설정(pom.xml)
 2) 공통 기능의 클래스 제작 – Advice 역할 클래스
 3) XML설정 파일에 Aspect 설정

pom.xml

```
<!-- AOP -->
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjweaver</artifactId>
			<version>1.7.4</version>
		</dependency>
```

C:\Users\pinka\.m2\repository\aopalliance\aopalliance\1.0\aopalliance-1.0.jar

applicationCTX.xml

namespace에서	xmlns:aop="http://www.springframework.org/schema/aop"

```
	<bean id="logAop" class="com.javalec.ex1.LogAop" />
	
//이 밑이 중요..//
	<aop:config>
		<aop:aspect id="logger" ref="logAop">
			<aop:pointcut id="publicM" expression="within(com.javalec.ex.*)"  />     //핵심코드....////////관점지향!!!!!
			<aop:around pointcut-ref="publicM" method="loggerAop" />
		</aop:aspect>
	</aop:config>

//객체 두개 만들기
	
	<bean id="student" class="com.javalec.ex1.Student" >
		<property name="name" value="홍길동" />
		<property name="age" value="10" />
		<property name="gradeNum" value="3" />
		<property name="classNum" value="5" />
	</bean>
	
	<bean id="worker" class="com.javalec.ex1.Worker" >
		<property name="name" value="홍길순" />
		<property name="age" value="35" />
		<property name="job" value="개발자" />
	</bean>

```


Logaop.java

```
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
```
예시
Student.getStudentInfo() is start.
이름 : 홍길동
나이 : 10
학년 : 3
반 : 5
Student.getStudentInfo() is finished.
Student.getStudentInfo() 경과시간 : 35
Worker.getWorkerInfo() is start.
이름 : 홍길순
나이 : 35
직업 : 개발자
Worker.getWorkerInfo() is finished.
Worker.getWorkerInfo() 경과시간 : 11


Advice 종류
--------

<aop:before> : 메소드 실행 전에 advice실행!!!!!
<aop:after-returning> : 정상적으로 메소드 실행 후에 advice실행
<aop:after-throwing> : 메소드 실행중 exception 발생시 advice실행
<aop:after> : 메소드 실행중 exception 이 발생하여도 advice실행
<aop:around> : 메서드 실행 전/후 및 exception 발생시 advice실행!!!!!!



