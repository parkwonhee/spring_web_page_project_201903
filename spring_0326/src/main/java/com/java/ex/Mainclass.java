package com.java.ex;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Mainclass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Calculation calculation = new Calculation();
		calculation.setFirstNum(10);
		calculation.setSecondNum(2);
		
		calculation.add();
		calculation.sub();
		calculation.mult();
		calculation.div();*/
/*add()
10 + 2 = 12
sub()
10 - 2 = 8
mult()
10 * 2 = 20
div()
10 / 2 = 5*/
		/*String configLoc = "classpath:applicationCTX.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLoc);
		//GenericXmlApplicationContext(configLoc); 
		//abstract pasing 해주는 것
		MyCalculator myCalculator = ctx.getBean("Mycalculator",MyCalculator.class);
		
		myCalculator.add();
		myCalculator.sub();
		myCalculator.mul();
		myCalculator.div();
		
		ctx.close();*/
/*addition()
10 + 2 = 12
subtraction()
10 - 2 = 8
multiplication()
10 * 2 = 20
division()
10 / 2 = 5
*/
		String configLocation = "classpath:applicationCTX.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		MyInfo myInfo = ctx.getBean("myInfo", MyInfo.class);
		myInfo.getInfo();
		ctx.close();
		
/*이름 : 홍길동
키 : 187.0
몸무게 : 84.0
취미 : [수영, 요리, 독서]
BMI 지수 : 24
정상 입니다.
*/
	}
}
