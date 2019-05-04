
 @RequestMapping 파라미터

--------------------

14-1) - @RequestMapping에서 Get방식과 Post방식

@RequestMapping에서 요청을 방을 때 Get방식과 Post방식을 구분 하기

RequestMethod.POST
id : dwdf
RequestMethod.GET
id : wdfwd
결과값이다.


HomeController.java

```
//get and post method
	  @RequestMapping("/index")
		public String goIndex() {
			return "index";
		}
	  
	  @RequestMapping(method = RequestMethod.GET, value = "/student") ///뒤에 값이 나오는 것
		public String goStudent(HttpServletRequest httpServletRequest, Model model) {
			
			System.out.println("RequestMethod.GET");
			
			String id = httpServletRequest.getParameter("id");
			System.out.println("id : " + id);
			model.addAttribute("studentId", id);
			
			return "join/studentView";
		}
		
		@RequestMapping(method = RequestMethod.POST, value = "/student") //값이 header에 있는것
		public ModelAndView goStudent(HttpServletRequest httpServletRequest) {
			
			System.out.println("RequestMethod.POST");
			
			String id = httpServletRequest.getParameter("id");
			System.out.println("id : " + id);
			
			ModelAndView mv = new ModelAndView();
			mv.setViewName("join/studentView");
			mv.addObject("studentId", id);
			
//			model.addAttribute("studentId", id);
			
//			return "join/studentView";
			return mv;
		}
```


14-2) -  @ModelAttribute

@ModelAttribute 어노테이션을 이용하면 커맨드 객체의 이름을 개발자가 변경 할 수 있다.

HomeController.java


```
		@RequestMapping("/insertstudent")
		public String index() {
			return "insertstudent";
		}
		
		@RequestMapping("/studentView")
		public String studentView(@ModelAttribute("studentInfo") StudentInformation studentInformation){
			return "studentView";
		}
```

insertstudent.jsp
//여기서 입력받기

```	
	<%
		String context = request.getContextPath();
	%>
	
	<form action="<%=context%>/studentView" method="post">
		이름 : <input type="text" name="name"><br />
		나이 : <input type="age" name="age"><br />
		학년 : <input type="classNum" name="classNum"><br />
		반 : <input type="gradeNum" name="gradeNum"><br />
		<input type="submit" value="전송">
	</form>
```

//http://localhost:8080/ex/studentView


14-3) -  리다이렉트(redirect: 키워드) 

다른 페이지로 이동할 때 사용 한다.

```
//http://localhost:8080/ex/indexs?id=abc
		//redirect
		@RequestMapping("/indexs")
		public String studentRedirect(HttpServletRequest httpServletRequest, Model model){
			
			String id = httpServletRequest.getParameter("id");
			if(id.equals("abc")) {
				return "redirect:studentOk";
			}
			
			return "redirect:studentNg";
		}
```


