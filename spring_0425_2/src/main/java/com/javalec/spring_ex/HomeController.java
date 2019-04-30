package com.javalec.spring_ex;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";//view의 이름
	}
	@RequestMapping("/board/view")
	public String view() {
		
		
		return "board/view";
	}
	
	@RequestMapping("/board/content")
	public String content(Model model) { //Model 객체를 파라미터로 받음
		
		model.addAttribute("id", 30);//Model 객체에 데이터를 담음

		return "board/content";//컨트롤러에서 전달 받은 Model객체의 속성을 이용함.

		
	}
	
	@RequestMapping("/board/reply")
	public ModelAndView reply() {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("id", 30);
		mv.setViewName("board/reply");
		
		return mv;
	}
	
}
/*@Controller
@RequestMapping("/board")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	

	@RequestMapping("/write")
	public String write(Model model) {
		
		model.addAttribute("id", 30);
		
		return "board/write";
	}
	
}*/
