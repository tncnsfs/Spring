package com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.Board;
import com.model.BoardDao;

@Controller
public class BoardController {
	private BoardDao dao;

	/*
	@RequestMapping(value="/insert_form.do", method=RequestMethod.GET)
	public String insertform(Model model){ //������Ÿ���� string�̶�� , Model���� �޴´�. ��, ������ �޴¹�� ������Ÿ��2���� : modelandview, string-model
		model.addAttribute("title", "�۾���2");
		
		return "insert_form";
	}*/
	
	@RequestMapping(value="/board_insert.do", method=RequestMethod.GET)
	public String insertform(@ModelAttribute("boardCommand") Board board, Model model){ //������Ÿ���� string�̶�� , Model���� �޴´�. ��, ������ �޴¹�� ������Ÿ��2���� : modelandview, string-model
		model.addAttribute("title", "�۾���2");
		
		return "insert_form";
	}
	
	public BoardDao getDao() {
		return dao;
	}
	@Autowired
	public void setDao(BoardDao dao) {
		this.dao = dao;
	}

	@RequestMapping(value="/board_insert.do", method=RequestMethod.POST)
	public String board_insert(@ModelAttribute("boardCommand") @Valid Board board, BindingResult errors){ 
		
		if(errors.hasErrors()) //���ε� ��ü�� ������ �ֳ�
		{
			System.out.println("���� �߻�");
			return "insert_form";
			
		}
		dao.insert(board);
		
		return "redirect:board_list";
	}
	
	@RequestMapping("/board_list")
	public String board_list(Model model){
		//����Ͻ� ó��
		List<Board> list = dao.listBoard();
		//������ ��������
		model.addAttribute("list", list);
		
		return "list";
		//�� �̸� ���ϱ�
	}

	/*@InitBinder
	protected void InitBinder(WebDataBinder binder){
		binder.setValidator(new BoardValidator());
	}*/

}
