#3.28
------

#DI활용_spring

applicationCTX.xml에 있는 

```
<bean id="student1" class="com.javalec.ex.Student">
		<constructor-arg>
			<value>홍길동</value>
		</constructor-arg>
		<constructor-arg>
			<value>10살</value>
		</constructor-arg>
		<constructor-arg>
			<value>3학년</value>
		</constructor-arg>
		<constructor-arg>
			<value>20번</value>
		</constructor-arg>
	</bean>

	<bean id="student2" class="com.javalec.ex.Student">
		<constructor-arg value="홍길동" />
		<constructor-arg value="9살" />
		<constructor-arg value="2학년" />
		<constructor-arg value="10번" />
	</bean>

	<bean id="studentInfo" class="com.javalec.ex.StudentInfo">
		<constructor-arg>
			<ref bean="student1" />
		</constructor-arg>
	</bean>

	<bean id="pencil" class="com.javalec.ex.Pencil6BWithEraser"></bean>
```

student.java
```
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

//fill setter getter
```

studendInof.java

```
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
```


mainclass

```
		/*String configLocatioin = "classpath:applicationCTX.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocatioin);
		StudentInfo studentInfo = ctx.getBean("studentInfo", StudentInfo.class);
		studentInfo.getStudentInfo();
		
		Student student2 = ctx.getBean("student2", Student.class);
		studentInfo.setStudent(student2);
		studentInfo.getStudentInfo();
		
		ctx.close();*/

//result
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
```

applicationCTX.xml에 있는 bean을 사용하였다

interface활용하기

```
public interface Pencil {
	public void use();
}
```

public class Pencil6B implements Pencil
public class Pencil6BWithEraser extends Pencil6B implements Pencil


mainclass.java

```
AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX.xml");
		Pencil pencil = ctx.getBean("pencil", Pencil.class);
		pencil.use();

		ctx.close();
		/*4B 굵기로 쓰입니다.
		 * 6B굵기로 쓰입니다.
		 * 6B굵기로 쓰이고, 지우개가 있습니다.*/
```
