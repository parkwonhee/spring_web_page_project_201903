package com.javalec.ex2;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String configLocation1 = "classpath:applicationCTX.xml";
		//String configLocation2 = "classpath:applicationCTX1.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation1);
		//AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation1, configLocation2);
		
		Student student1 = ctx.getBean("student4", Student.class);
		System.out.println(student1.getName());	//홍길동
		System.out.println(student1.getHobbys());	// 수영, 요리
		
		StudentInfo studentInfo = ctx.getBean("sutudentInfo1", StudentInfo.class);
		Student student2 = studentInfo.getStudent();	//student1  == student2
		System.out.println(student2.getName());	//홍길동
		System.out.println(student2.getHobbys());	// 수영, 요리
		
		if(student1.equals(student2)) {
			System.out.println("student4 == student2");
		}
		
		Student student3 = ctx.getBean("student3", Student.class);
		System.out.println(student3.getName());
		
		if(student1.equals(student3)) {
			System.out.println("student4 == student3");
		} else {
			System.out.println("student4 != student3");
		}
		
		Family family = ctx.getBean("family", Family.class);
		System.out.println(family.getPapaName());
		System.out.println(family.getMamiName());
		System.out.println(family.getSisterName());
		System.out.println(family.getBrotherName());
		
		ctx.close();
	}
/*홍길동
[수영, 요리]
홍길동
[수영, 요리]
student4 == student2
홍길자
student4 != student3
홍아빠
홈엄마
홍누나
홍오빠*/
}
