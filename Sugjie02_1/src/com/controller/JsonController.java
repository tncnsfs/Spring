package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Board;
import com.model.BoardDao;
import com.model.Member;

//���� json ����� ������̼� + lib �ϳ� �߰� 
@RestController
public class JsonController {
	
	private BoardDao dao;
	
	@Autowired
	public void setDao(BoardDao dao) {
		this.dao = dao;
	}
	
	
	// Json ����� 
	@RequestMapping("spring_json")
	public List<Member> spring_json(){
		List<Member> list = new ArrayList<>();
		list.add(new Member("ȫ�浿", "aa@aa.com"));
		list.add(new Member("�ڱ浿", "bb@bb.com"));
		
		return list;
	}
	
	// Json ����� 
	@RequestMapping("boardJson")
	public List<Board> boardJson(Model model){
		List<Board> list = dao.listBoard();
		
		model.addAttribute("list", list);
		
		return list;
	}


}
