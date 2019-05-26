
security_setting.md
------------------------------------------------------------

security

25-1. 보안 관련 프로젝트 생성

프로젝트 생성
한글 처리
보안 관련 라이브러리 추가
스프링 설정 파일 분리
보안 관련 설정 파일 만들기

25-2. 보안 관련 라이브러리 추가

security

poem.xml

```
<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-config</artifactId>
			<version>3.2.5.RELEASE</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-core</artifactId>
			<version>3.2.5.RELEASE</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-web</artifactId>
			<version>3.2.5.RELEASE</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-taglibs</artifactId>
			<version>3.2.4.RELEASE</version>
		</dependency>
```

25-3. 보안 관련 설정 파일 만들기

security-context

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:security="http://www.springframework.org/schema/security"
	xsi:schemaLocation="http://www.springframework.org/schema/beans
					http://www.springframework.org/schema/beans/spring-beans.xsd
					http://www.springframework.org/schema/security
					http://www.springframework.org/schema/security/spring-security-3.2.xsd">

	<security:http auto-config="true">
		<security:intercept-url pattern="/login.html*" access="ROLE_USER"/>
//이건 사용자의 인증하에 특정페이지를 오픈할 수 있다.
		<security:intercept-url pattern="/welcome.html*" access="ROLE_ADMIN"/>
//이건 관리자의 인증하에 특정페이지를 오픈할 수 있다.
	</security:http>
	
	<security:authentication-manager>
		<security:authentication-provider>
			<security:user-service>
				<security:user name="user" password="123" authorities="ROLE_USER"/>
				<security:user name="admin" password="123" authorities="ROLE_ADMIN,ROLE_USER"/>
			</security:user-service>
		</security:authentication-provider>
	</security:authentication-manager>

</beans>
```

설정 web.xml

```
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>
			/WEB-INF/spring/root-context.xml
			/WEB-INF/spring/appServlet/security-context.xml
			</param-value>
	</context-param>
```

25-4. In-Memory 인증

설정 파일 작성

web.xml에 filter 작성

	<security:http auto-config="true">
		<security:intercept-url pattern="/login.html*" access="ROLE_USER"/>
		<security:intercept-url pattern="/welcome.html*" access="ROLE_ADMIN"/>
	</security:http>
	
	<security:authentication-manager>
		<security:authentication-provider>
			<security:user-service>
				<security:user name="user" password="123" authorities="ROLE_USER"/>
				<security:user name="admin" password="123" authorities="ROLE_ADMIN,ROLE_USER"/>
			</security:user-service>
		</security:authentication-provider>
	</security:authentication-manager>

```
