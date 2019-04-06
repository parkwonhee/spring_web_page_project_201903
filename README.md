# spring_web_page_project_201903
spring_web_page_project_201903
---------------------------------------


spring

DI (dependency injection)=의존주입,,,와 IOC컨테이너(부품을 넣는 곳)

xml을 이용한 di설정방법
1. a객체에서 new b와 new c를 직접 생성
2. a라는 객체가 b와 c의 field를 만들어 놓는다.
그 field에 대해서 setter() 나 construct(생성자)로 this.b로 받는다.
즉, 외부에서 객체를 넣어준다.---주입!!

스프링이란 부품을 생산하고 조립하는 라이브러리 집합체라고 할 수 있다.!!
 =생성과 조립
교모가 크고,유지보수가 용이하다.

type 자격--interface 구조가 중요하다.

java code는 바뀌지 않는다.
xml에서 클래스만 바뀐다.//스프링 설정파일만 바뀌여부품들을 생성조립한다.

		String configLocatioin = "classpath:applicationCTX.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocatioin);
		StudentInfo studentInfo = ctx.getBean("studentInfo", StudentInfo.class);
		studentInfo.getStudentInfo();

constructor-생성자 c:굳이..??
property-setter

java을 이용한 di설정방법
annotation
이클래스는 스프링설정에 사용하는 클래스입니다....configuration
@Bean

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		Student student1 = ctx.getBean("student1", Student.class);

0403
mainㅡclass
```
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
```
pencil.class
```
package com.javalec.ex;

public interface Pencil {
	public void use();
}
public class Pencil4B implements Pencil{
	@Override
	public void use() {
		System.out.println("4B 굵기로 쓰입니다.");
	}
}
public class Pencil6B implements Pencil{
	@Override
	public void use() {
		System.out.println("6B굵기로 쓰입니다.");
	}
}
public class Pencil6BWithEraser extends Pencil6B implements Pencil{
	@Override
	public void use() {
//		super.use();
		System.out.println("6B굵기로 쓰이고, 지우개가 있습니다.");
	}
}
```

```
public class Student {
	private String name;
	private String age;
	private String gradeNum;
	private String classNum;
	
	public Student(String name, String age, String gradeNum, String classNum) {
		this.name = name;
		this.age =  age;
		this.gradeNum = gradeNum;
		this.classNum = classNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGradeNum() {
		return gradeNum;
	}

	public void setGradeNum(String gradeNum) {
		this.gradeNum = gradeNum;
	}

	public String getClassNum() {
		return classNum;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
}
```

studentInfo
```
public class StudentInfo {
private Student student;
	
	public StudentInfo(Student student) {
		this.student = student;
	}
	
	public void getStudentInfo(){
		if(student != null) {
			System.out.println("이름 : " + student.getName());
			System.out.println("나이 : " + student.getAge());
			System.out.println("학년 : " + student.getGradeNum());
			System.out.println("반 : " + student.getClassNum());
			System.out.println("======================");
		}
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}
}

```

외부 파일을 이용한 설정
8-1. Environment 객체

ctx.getEnvironment
env.getPropertySources
프로퍼티 추가 및 추출에 대한 내용
추가 : propertySources.addLast()
추출 : env.getProperty()


```
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

public class AdminConnection implements EnvironmentAware, InitializingBean, DisposableBean{
	private Environment env;
	private String adminId;
	private String adminPw;
	
	@Override
	public void setEnvironment(Environment env) {
		//implements EnvironmentAware를 하는 순간 이것이  initializingBean 보다 더 먼저 실행된다.
		//여기서 최초의 객체를 얻을 수 있다.
		System.out.println("setEnvironment()");
		setEnv(env);//내가 외부에 설정한 파일에 있는 동일한 값을 넣는다.
	}
	
	public void setEnv(Environment env) {
		this.env = env;
	}
	
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	
	public void setAdminPw(String adminPw) {
		this.adminPw = adminPw;
	}
	
	public String getAdminId() {
		return adminId;
	}
	
	public String getAdminPw() {
		return adminPw;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet()");
		setAdminId(env.getProperty("admin.id"));//여기다 set을 한다.
		setAdminPw(env.getProperty("admin.pw"));
	}

	@Override
	public void destroy() throws Exception {//close하면 이것이 실행된다.
		System.out.println("destroy()");
	}
}
```

mainclass
```
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
```

admin.properties
admin.id=abcde
admin.pw=12345

applicationCTX.xml
		<bean id="adminConnection" class="spring_0403.ex1.AdminConnection" />
를 사용한다.
