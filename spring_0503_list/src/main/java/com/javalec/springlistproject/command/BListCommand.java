package com.javalec.springlistproject.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.javalec.springlistproject.dao.BDao;
import com.javalec.springlistproject.dto.BDto;

public class BListCommand implements BCommand {
	//dbcp방법으로 한다!!
	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
		BDao dao = new BDao();
		ArrayList<BDto> dtos = dao.list();
		model.addAttribute("list", dtos);
	}

}
