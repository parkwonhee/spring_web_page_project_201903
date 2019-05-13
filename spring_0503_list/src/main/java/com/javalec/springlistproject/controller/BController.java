package com.javalec.springlistproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javalec.springlistproject.command.BCommand;
import com.javalec.springlistproject.command.BContentCommand;
import com.javalec.springlistproject.command.BListCommand;
import com.javalec.springlistproject.command.BModifyCommand;
import com.javalec.springlistproject.command.BReplyCommand;
import com.javalec.springlistproject.command.BWriteCommand;

@Controller
public class BController {

BCommand command;
	
	@RequestMapping("/list") //리스트화면
	public String list(Model model) {
		System.out.println("list()");
		
		command = new BListCommand();
		command.execute(model);
		return "list";
	}
	
	@RequestMapping("/write_view")//글작성화면
	public String wirte_view(Model model) {
		System.out.println("write_view()");
		
		return "write_view";
	}
	
	@RequestMapping("/write")//작성행동
	public String write(HttpServletRequest request,Model model) {
		
		System.out.println("write()");
		
		model.addAttribute("request",request);
		command = new BWriteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/content_view")//내용
	public String content_view(HttpServletRequest request,Model model) {
		System.out.println("content_view()");
		
		model.addAttribute("request",request);
		command = new BContentCommand();
		command.execute(model);
		
		return "content_view";
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/modify") //수정
	public String modify(HttpServletRequest request,Model model) {
		System.out.println("modify()");
		
		model.addAttribute("request",request);
		command = new BModifyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view") //답변창
	public String reply_view(HttpServletRequest request,Model model) {
		System.out.println("reply_view()");
		
		model.addAttribute("request",request);
		command = new BReplyCommand();
		command.execute(model);
		
		return "reply_view";
	}
	
	@RequestMapping("/reply") //답변 하는것
	public String reply(HttpServletRequest request,Model model) {
		System.out.println("reply()");
		
		model.addAttribute("request",request);
		command = new BReplyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete") //삭제하기
	public String delete(HttpServletRequest request,Model model) {
		System.out.println("delete()");
		
		model.addAttribute("request",request);
		command = new BReplyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	//39
}
