package com.javalex.ex2;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args){
		
		/*GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();

		ctx.load("classpath:applicationCTX.xml");
		
		ctx.refresh();
		
		ctx.close();*/
		/*afterPropertiesSet()
		initMethod()
		destroyMethod()
		destroy()*/	
		
		
		//7-3스프링 빈 범위(scope)
		//, 스프링 빈이 생성 될 때, 생성된 스프링 빈은 scope을 가지고 있습니다.
		//범위란 쉽게 생각해서 해당하는 객체가 어디까지 영향을 미치는지 결정하는 것이라고 생각하면 됩니다.

		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		
		Student student1 = ctx.getBean("student11", Student.class);
		System.out.println("이름 : " + student1.getName());
		System.out.println("나이 : " + student1.getAge());
		
		System.out.println("==============================");
		
		Student student2 = ctx.getBean("student11", Student.class);
		student2.setName("홀길자");
		student2.setAge(100);
		
		System.out.println("이름 : " + student1.getName());//student2도 같은 값이 나온다.!!!!!! 즉. 같은 값을 바라본다...
		System.out.println("나이 : " + student1.getAge());
		
		System.out.println("==============================");

		if(student1.equals(student2)) {
			System.out.println("student1 == student2");
		} else {
			System.out.println("student1 != student2");
		}
		
		ctx.close();
		/*afterPropertiesSet()
		initMethod()
		afterPropertiesSet()
		이름 : 홍길순
		나이 : 30
		==============================
		이름 : 홀길자
		나이 : 100
		==============================
		student1 == student2
		destroy()
		destroyMethod()
		destroy()*/
		}
}
