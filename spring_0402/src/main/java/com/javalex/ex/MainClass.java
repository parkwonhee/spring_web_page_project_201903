package com.javalex.ex;

import org.springframework.context.support.GenericXmlApplicationContext;

//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//이것은 Configuration일때만


//import org.springframework.context.support.AbstractApplicationContext;
//import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		//5강 -2
		// TODO Auto-generated method stub
		/*AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		Student student1 = ctx.getBean("student1", Student.class);
		System.out.println("이름 : " + student1.getName());
		System.out.println("나이 : " + student1.getAge());
		System.out.println("취미 : " + student1.getHobbys());
		System.out.println("신장 : " + student1.getHeight());
		System.out.println("몸무게 : " + student1.getWeight());
		
		Student student2 = ctx.getBean("student2", Student.class);
		System.out.println("이름 : " + student2.getName());
		System.out.println("나이 : " + student2.getAge());
		System.out.println("취미 : " + student2.getHobbys());
		System.out.println("신장 : " + student2.getHeight());
		System.out.println("몸무게 : " + student2.getWeight());
	
		ctx.close();*/
		
		/*Configuration을 사용한다.
		 * 이름 : 홍길동
		나이 : 20
		취미 : [수영, 요리]
		신장 : 180.0
		몸무게 : 80.0
		이름 : 홍길순
		나이 : 18
		취미 : [독서, 음악감상]
		신장 : 170.0
		몸무게 : 55.0*/
		
		/*AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Student student1 = ctx.getBean("student1", Student.class);
		System.out.println("이름 : " + student1.getName());
		System.out.println("나이 : " + student1.getAge());
		System.out.println("취미 : " + student1.getHobbys());
		System.out.println("키 : " + student1.getHeight());
		System.out.println("몸무게 : " + student1.getWeight());
		
		Student student2 = ctx.getBean("student2", Student.class);
		System.out.println("이름 : " + student2.getName());
		System.out.println("나이 : " + student2.getAge());
		System.out.println("취미 : " + student2.getHobbys());
		System.out.println("키 : " + student2.getHeight());
		System.out.println("몸무게 : " + student2.getWeight());
		
		Student student3 = ctx.getBean("student3", Student.class);
		System.out.println("이름 : " + student2.getName());
		System.out.println("나이 : " + student2.getAge());
		System.out.println("취미 : " + student2.getHobbys());
		System.out.println("키 : " + student2.getHeight());
		System.out.println("몸무게 : " + student2.getWeight());
		
		ctx.close();*/
		
		/*AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		Student student1 = ctx.getBean("student1", Student.class);
		System.out.println("이름 : " + student1.getName());
		System.out.println("나이 : " + student1.getAge());
		System.out.println("취미 : " + student1.getHobbys());
		System.out.println("키 : " + student1.getHeight());
		System.out.println("몸무게 : " + student1.getWeight());
		
		Student student2 = ctx.getBean("student2", Student.class);
		System.out.println("이름 : " + student2.getName());
		System.out.println("나이 : " + student2.getAge());
		System.out.println("취미 : " + student2.getHobbys());
		System.out.println("키 : " + student2.getHeight());
		System.out.println("몸무게 : " + student2.getWeight());
		
		Student student3 = ctx.getBean("student3", Student.class);
		System.out.println("이름 : " + student2.getName());
		System.out.println("나이 : " + student2.getAge());
		System.out.println("취미 : " + student2.getHobbys());
		System.out.println("키 : " + student2.getHeight());
		System.out.println("몸무게 : " + student2.getWeight());
		
		ctx.close();*/
		/*
		 * 이름 : 홍길동
		나이 : 20
		취미 : [수영, 요리]
		키 : 180.0
		몸무게 : 80.0
		이름 : 홍길순
		나이 : 18
		취미 : [독서, 음악감상]
		키 : 170.0
		몸무게 : 55.0
		이름 : 홍길순
		나이 : 18
		취미 : [독서, 음악감상]
		키 : 170.0
		몸무게 : 55.0*/
		
		
		//6강 생명주기
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();	// 생성

		ctx.load("classpath:applicationCTX.xml");	// 설정
		
		ctx.refresh(); //설정=---load가 있으면 반드시 있어야 한다.----이때 생성된다....
		
		Student student = ctx.getBean("student3", Student.class);	// 사용
		System.out.println("이름 : " + student.getName());
		System.out.println("나이 : " + student.getAge());
		
		ctx.close();								// 종료
		
		/*이름 : 홍길순
		나이 : 30*/
		
	}
}
