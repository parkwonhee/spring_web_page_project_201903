package com.javalec.springlistproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javalec.springlistproject.command.BCommand;
import com.javalec.springlistproject.command.BContentCommand;
import com.javalec.springlistproject.command.BDeleteCommand;
import com.javalec.springlistproject.command.BListCommand;
import com.javalec.springlistproject.command.BModifyCommand;
import com.javalec.springlistproject.command.BReplyCommand;
import com.javalec.springlistproject.command.BReplyViewCommand;
import com.javalec.springlistproject.command.BWriteCommand;
import com.javalec.springlistproject.util.Constant;

@Controller
public class BController {

	BCommand command;
	
	//jdbc tamplate
	private JdbcTemplate template;
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template = this.template;
	}
	
	@RequestMapping("/list") //����Ʈȭ��
	public String list(Model model) {
		System.out.println("list()");
		
		command = new BListCommand();
		System.out.println("list()2");
		command.execute(model);
		System.out.println("list()3");
		return "list";
	}
	
	@RequestMapping("/write_view")//���ۼ�ȭ��
	public String wirte_view(Model model) {
		System.out.println("write_view()");
		
		return "write_view";
	}
	
	@RequestMapping("/write")//�ۼ��ൿ
	public String write(HttpServletRequest request,Model model) {
		
		System.out.println("write()");
		
		model.addAttribute("request",request);
		command = new BWriteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/content_view")//����
	public String content_view(HttpServletRequest request,Model model) {
		System.out.println("content_view()");
		
		model.addAttribute("request",request);
		command = new BContentCommand();
		command.execute(model);
		
		return "content_view";
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/modify") //�����ϱ�
	public String modify(HttpServletRequest request,Model model) {
		System.out.println("modify()");
		
		model.addAttribute("request",request);
		command = new BModifyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete") //�����ϱ�
	public String delete(HttpServletRequest request,Model model) {
		System.out.println("delete()");
		
		model.addAttribute("request", request);
		command = new BDeleteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")//�亯â
	public String reply_view(HttpServletRequest request, Model model){
		System.out.println("reply_view()");
		
		model.addAttribute("request", request);
		command = new BReplyViewCommand();
		command.execute(model);
		
		return "reply_view";
	}
	
	@RequestMapping("/reply")//�亯�ϴ°�
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("reply()");
		
		model.addAttribute("request", request);		
		command = new BReplyCommand();
		command.execute(model);
		
		return "redirect:list";
	}

	
	//39
}
