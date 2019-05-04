


-----------------------------------------------------------------------------
컨트롤러
Controller
------

최초 클라이언트로부터 요청이 들어왔을 때, 컨트롤러로 진입하게 된다.
그리고 컨트롤러는 요청에 대한 작업을 한 후 뷰쪽으로 데이터를 전달한다.


1. @Controller를 이용한 클래스 생성

2. @RequestMapping을 이용한 요청 경로 지정

3. 요청 처리 메소드 구현

4.뷰 이름 리턴

한글처리 web.xml


```
	<filter>
		<filter-name>encodingFilter</filter-name>
		<filter-class>
			org.springframework.web.filter.CharacterEncodingFilter     
		</filter-class>
		<init-param>
			<param-name>encoding</param-name>   
			<param-value>UTF-8</param-value>
		</init-param>
		<init-param>
			<param-name>forceEncoding</param-name>  
			<param-value>true</param-value>
		</init-param>
	</filter>    

	<filter-mapping>
		<filter-name>encodingFilter</filter-name>
		<url-pattern>/*</url-pattern>                 
	</filter-mapping>
```

- 뷰페이지 이름 생성(조합) 방법

뷰페이지 이름 = prefix + 요청처리 메소드 반환값 + suffix

```
<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<beans:property name="prefix" value="/WEB-INF/views/" />
		<beans:property name="suffix" value=".jsp" />
	</beans:bean>
```



-- 뷰에 데이터 전달

```
@RequestMapping("/board/content")
	public String content(Model model) {
```

- Model 객체를 파라미터로 받음

- Model 객체에 데이터를 담음  (속성을 담는다.)

```
model.addAttribute("id", 30);
```

- 컨트롤러에서 전달 받은 Model객체의 속성을 이용함.

```
<body>
	content.jsp 입니다. <br />
	id : ${id}
</body>
```

- 뷰에 데이터 전달

- ModelAndView 클래스를 이용한 데이터 전달


```
public ModelAndView reply() {
		
		ModelAndView mv = new ModelAndView(); //ModelAndView 객체 생성
		mv.addObject("id", 30);//Model 객체에 데이터를 담음
		mv.setViewName("board/reply");//뷰이름 설정
		
		return mv;
	}
```

ModelAndView 객체 생성


- 클래스에 @RequestMapping적용
---

```
@Controller
@RequestMapping("/board")
public class HomeController {
```




