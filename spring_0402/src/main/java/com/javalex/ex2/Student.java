package com.javalex.ex2;
//7강 -2 
import org.springframework.beans.factory.DisposableBean;//빈 초기와 과정에서 호출
import org.springframework.beans.factory.InitializingBean;//빈 소멸 과정에서 호출

public class Student implements InitializingBean, DisposableBean{
	private String name;
	private int age;
	
	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	//7-3강
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("afterPropertiesSet()");
	}

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("destroy()");
	}
}
