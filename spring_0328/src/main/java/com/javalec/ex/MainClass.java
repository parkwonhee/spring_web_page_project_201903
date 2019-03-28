package com.javalec.ex;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*String configLocatioin = "classpath:applicationCTX.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocatioin);
		StudentInfo studentInfo = ctx.getBean("studentInfo", StudentInfo.class);
		studentInfo.getStudentInfo();
		
		Student student2 = ctx.getBean("student2", Student.class);
		studentInfo.setStudent(student2);
		studentInfo.getStudentInfo();
		
		ctx.close();*/
		/*이름 : 홍길동
		나이 : 10살
		학년 : 3학년
		반 : 20번
		======================
		이름 : 홍길동
		나이 : 9살
		학년 : 2학년
		반 : 10번
		======================*/
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Pencil pencil = ctx.getBean("pencil", Pencil.class);
		pencil.use();
		
		ctx.close();
		/*4B 굵기로 쓰입니다.
		 * 6B굵기로 쓰입니다.
		 * 6B굵기로 쓰이고, 지우개가 있습니다.*/
	}
}
