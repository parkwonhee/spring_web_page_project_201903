package spring_0403.ex1;
//8-1 enviroment객체를 사용하여 외부파일 이용하여 설정하기
import java.io.IOException;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConfigurableApplicationContext ctx = new GenericXmlApplicationContext();
		//인터페이스를 활용하다. ConfigurableApplicationContext context를 구한다.
		ConfigurableEnvironment env = ctx.getEnvironment();
		MutablePropertySources propertySources = env.getPropertySources();
		//enviroment는 property를 여러개 객체를 갖는데 그것을 구하는 명령어이다.
		
		try {
			propertySources.addLast(new ResourcePropertySource("classpath:admin.properties"));
			//내가 만든 환경설정이 뒤에 따라 다니도록 담는다.
			
			System.out.println( env.getProperty("admin.id") );//제대로 들어갔나 확인하기
			System.out.println( env.getProperty("admin.pw") );
		} catch (IOException e) {}
		
		GenericXmlApplicationContext gCtx = (GenericXmlApplicationContext)ctx;
		gCtx.load("applicationCTX.xml");
		gCtx.refresh();//load하면 무조건 빈객체가 생성된다.
		
		AdminConnection adminConnection = gCtx.getBean("adminConnection", AdminConnection.class);
		System.out.println("admin ID : " + adminConnection.getAdminId());
		System.out.println("amdin PW : " + adminConnection.getAdminPw());
		
		gCtx.close();
		ctx.close();
	}
/*abcde
12345
setEnvironment()
afterPropertiesSet()
admin ID : abcde
amdin PW : 12345
destroy()
*/

}
