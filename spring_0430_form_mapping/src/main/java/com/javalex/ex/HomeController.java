package com.javalex.ex;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.javalex.ex.member.Member;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping("board/confirmId")
	public String confirmId(HttpServletRequest httpServletRequest, Model model) {
		// (앞은 변수 받을때, 뒤은 변수내놓을때)

		String id = httpServletRequest.getParameter("id");
		String pw = httpServletRequest.getParameter("pw");

		model.addAttribute("id", id);
		model.addAttribute("pw", pw);

		return "board/confirmId";
	}

	@RequestMapping("board/checkId")
	public String checkId(@RequestParam("id") String id, @RequestParam("pw") int pw, Model model) {
		model.addAttribute("identify", id);
		model.addAttribute("password", pw);

		return "board/checkId";
	}
	// http://localhost:8080/ex/board/checkId?id=123&pw=2341

	
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
	 

	/*
	 * @RequestMapping("/join/formOK") public String joinData(Member member) {
	 * 
	 * return "join/formOK"; }
	 */
	// http://localhost:8080/ex/join/formOK?name=wef&id=234&pw=vwf&email=vwddk
	  
	  @RequestMapping("student/{studentId}")
		public String getStudent(@PathVariable String studentId, Model model) {
			
			model.addAttribute("studentId", studentId);
			return "join/studentView";//jsp파일 여기로 가시오
			
		}
	  //http://localhost:8080/ex/student/10
	  
	  //get and post method
	  @RequestMapping("/index")
		public String goIndex() {
			return "index";
		}
	  
	  @RequestMapping(method = RequestMethod.GET, value = "/student")
		public String goStudent(HttpServletRequest httpServletRequest, Model model) {
			
			System.out.println("RequestMethod.GET");
			
			String id = httpServletRequest.getParameter("id");
			System.out.println("id : " + id);
			model.addAttribute("studentId", id);
			
			return "join/studentView";
		}
		
		@RequestMapping(method = RequestMethod.POST, value = "/student")
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
		
		@RequestMapping("/insertstudent")
		public String index() {
			return "insertstudent";
		}
		
		@RequestMapping("/studentView")
		public String studentView(@ModelAttribute("studentInfo") StudentInformation studentInformation){
			return "studentView";
		}
		//http://localhost:8080/ex/studentView
		
		
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
		
		@RequestMapping("/studentOk")
		public String studentOk(Model model){
			
			return "student/studentOk";
		}
		
		@RequestMapping("/studentNg")
		public String studentNg(Model model){
			
			return "student/studentNg";
		}
		
		@RequestMapping("/studentURL2")
		public String studentURL2(Model model) {
			
			return "redirect:student/studentURL2";
		}
		
		@RequestMapping("/student/studentURL2")
		public String studentURL22(Model model) {
			
			return "redirect:http://localhost:8181/ex/student/studentURL2.jsp";
		}
}
