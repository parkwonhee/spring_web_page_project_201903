

#SPRING MVC BASIC
--------------------

스프링이 유명하게 된 계기는 아마도 웹 애플리케이션 제작에 적용되면서 웹 프레임워크로서 우수성이 인정되었기 때문이다.
웹 애플리케이션 제작을 위한 스프링 MVC이란?


 - DispatcherServlet = client요청을 최초로 받는 부분
			1)클라이언트의 요청을 최초 받아 
			2)컨트롤러에게 전달


- Controller = (ModelAndView)

- View(JSP)

mvc project 만들기


web.xml = 1) DispatcherServlet 서블릿 맵핑
	  2) 스프링 설정 파일 위치 정의

```
<servlet>
		<servlet-name>appServlet</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<init-param>
			<param-name>contextConfigLocation</param-name>
			<param-value>/WEB-INF/spring/appServlet/servlet-context.xml</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>
		
	<servlet-mapping>
		<servlet-name>appServlet</servlet-name>
		<url-pattern>/</url-pattern>
	</servlet-mapping>
```
여기서  /(최초요청)는 DispatcherServlet로 보낸다....





HomeController.java

```
@Controller
public class HomeController  =  Dispatcher에서 전달된 요청을 처리



return "home";//view의 이름
```

servlet-context.xml    =  스프링 컨테이너 설정 파일

```

	<!-- Handles HTTP GET requests for /resources/** by efficiently serving up static resources in the ${webappRoot}/resources directory -->
	<resources mapping="/resources/**" location="/resources/" />

///////resources의 경로는 Dispatcher로 가지 않고 resources에서 검색을...찾아라!!


	<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<beans:property name="prefix" value="/WEB-INF/views/" />
		<beans:property name="suffix" value=".jsp" />
	</beans:bean>

	<context:component-scan base-package="com.javalec.spring_ex" />
```

밑에 component-scan 에서 com.javalec.spring_ex에서 스캔하면 @Controller를 만나면 
이파일이 controller역할을 하는구나 라는것을 알게된다.

결론 /WEB-INF/views/ + home + .jsp
로 view로 간다.


view.jsp

```
<p> <img src="resources/img/sba_logo.png"></p>

<p> <img src="resources01/img/sba_logo.png"></p>

```

결론
Hello world!
The time on the server is 2019? 4? 25? (?) ?? 11? 23? 32?.


