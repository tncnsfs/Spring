package com.controller;

import java.util.List;

import javax.jws.WebParam.Mode;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	public String insertform(@ModelAttribute("boardCommand") Board board, Model model){ 
		//������Ÿ���� string�̶�� , Model���� �޴´�. ��, ������ �޴¹�� ������Ÿ��2���� : modelandview, string-model
		model.addAttribute("title", "�۾���2");
		
		return "insert_form";
	}
	
	@RequestMapping(value="/board_update.do", method=RequestMethod.GET)
	public String update_form(@ModelAttribute("boardCommand") Board board, Model model){ 
		//������Ÿ���� string�̶�� , Model���� �޴´�. ��, ������ �޴¹�� ������Ÿ��2���� : modelandview, string-model
		model.addAttribute("message1", "�����۾���");
		
		return "update_form";
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
		dao.insertBoard(board);
		
		return "redirect:board_list.do";
	}
	
	
	
	
/*	@RequestMapping(value="/board_update.do", method=RequestMethod.POST)
	public String board_update(@ModelAttribute("boardCommand") @Valid Board board, BindingResult errors){ 
		
		if(errors.hasErrors()) //���ε� ��ü�� ������ �ֳ�
		{
			System.out.println("���� �߻�");
			return "update_form";
			
		}
		dao.updateBoard(board);
		
		return "redirect:board_list.do";
	}*/
	
	
	
//	@RequestMapping(value="/board_list.do", method=RequestMethod.GET)
	@RequestMapping("/board_list.do")
	public String board_list(Model model){
		//����Ͻ� ó��
		List<Board> list = dao.listBoard();
		//������ ��������
		model.addAttribute("list", list);
		return "list";
		//�� �̸� ���ϱ�
	}
	
	
	@RequestMapping("/detail.do")
	public ModelAndView detailBoard(int seq){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("detail");
		Board board = dao.detailBoard(seq);
		System.out.println("seq��: " + seq);
		
		mv.addObject("seq", board.getSeq());
		mv.addObject("title", board.getTitle());
		mv.addObject("writer", board.getWriter());
		mv.addObject("contents", board.getContents());
		System.out.println("board.getSeq: " + board.getSeq());
		return mv;
	}
	

	
	
}
