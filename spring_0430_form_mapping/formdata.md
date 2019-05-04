
-------------------------------------------------------------------

#form data
---


1) - HttpServletRequest 클래스

HttpServletRequest클래스를 이용해서 데이터를 전송하는 방법

```
	@RequestMapping("board/confirmId")
	public String confirmId(HttpServletRequest httpServletRequest, Model model) {
		//(앞은 변수 받을때, 뒤은 변수내놓을때)

		String id = httpServletRequest.getParameter("id");
		String pw = httpServletRequest.getParameter("pw");

		model.addAttribute("id", id);
		model.addAttribute("pw", pw);

		return "board/confirmId";
	}
```

2) - @RequestParam 어노테이션

@RequestParam 어노테이션을 이용해서 데이터를 전송하는 방법
	
```
	@RequestMapping("board/checkId")
	public String checkId(@RequestParam("id") String id, @RequestParam("pw") int pw, Model model) {
		model.addAttribute("identify", id);
		model.addAttribute("password", pw);
		return "board/checkId";
	}
```

	//http://localhost:8080/ex/board/checkId?id=123&pw=2341


3) - . 데이터(커맨드) 객체

가장 많이쓰이는 것

데이터(커맨드) 객체를 이용하여 데이터 많을 경우 간단하게 사용 할 수 있다.

일단 member객체

```
public class Member {
	
	private String name;
	private String id;
	private String pw;
	private String email;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
```

http://localhost:8080/ex/join/formOK?name=wef&id=234&pw=vwf&email=vwddk

 - 기존 방법 : 다소 코드양이 많다.

```
	@RequestMapping("join/formOK") 
	  public String join(@RequestParam("name") String name, @RequestParam("id") String id, @RequestParam("pw") String pw, @RequestParam("email") String email, Model model) {
	  
	  Member member = new Member(); 
	  member.setName(name); 
	  member.setId(id);
	  member.setPw(pw); 
	  member.setEmail(email);
	  
	  model.addAttribute("member", member);//이것 확인
	  
	  return "join/formOK"; 
	  }
```

 - 개선 방법 : 코드양이 적다.


```
	@RequestMapping("/member/join")
	public String joinData(Member member) {
		
		return "member/join";
	}
```

4) - @PathVariable

@PathValriable 어노테이션을 이용하면 경로(path)에 변수를 넣어 요청메소드에서 파라미터로 이용 할 수 있다.


```
	  @RequestMapping("student/{studentId}")
		public String getStudent(@PathVariable String studentId, Model model) {
			
			model.addAttribute("studentId", studentId);
			return "join/studentView";//jsp파일 여기로 가시오
			
		}
	  //http://localhost:8080/ex/student/10
```
