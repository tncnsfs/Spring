 package com.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebParam.Mode;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.model.Board;
import com.model.BoardDao;

@Controller
public class BoardController {
	private BoardDao dao;
	private String uploadDir = "D:/upload";
	

	/*
	@RequestMapping(value="/insert_form.do", method=RequestMethod.GET)
	public String insertform(Model model){ //������Ÿ���� string�̶�� , Model���� �޴´�. ��, ������ �޴¹�� ������Ÿ��2���� : modelandview, string-model
		model.addAttribute("title", "�۾���2");
		
		return "insert_form";
	}*/
	
	/*@RequestMapping(value="/board_insert.do", method=RequestMethod.GET)*/
	@RequestMapping(value="/board_insert", method=RequestMethod.GET)
	public String insertform(@ModelAttribute("boardCommand") Board board, Model model){ 
		//������Ÿ���� string�̶�� , Model���� �޴´�. ��, ������ �޴¹�� ������Ÿ��2���� : modelandview, string-model
		model.addAttribute("title", "�۾���2");
		
		return "insert_form";/*���ϰ��� tiles-definitions ���ϰ��� */
	}
	
	public BoardDao getDao() {
		return dao;
	}
	@Autowired
	public void setDao(BoardDao dao) {
		this.dao = dao;
	}
	

	/*@RequestMapping(value="/board_insert.do", method=RequestMethod.POST)*/
	@RequestMapping(value="/board_insert", method=RequestMethod.POST)
	public String board_insert(@ModelAttribute("boardCommand") @Valid Board board, BindingResult errors){ 
		
		if(errors.hasErrors()) //���ε� ��ü�� ������ �ֳ�
		{
			System.out.println("���� �߻�");
			return "insert_form";
			/*���ϰ��� tiles-definitions ���ϰ��� */
		}
		
		MultipartFile multipartFile = board.getUploadFile();
		if(multipartFile != null){
			String fname = multipartFile.getOriginalFilename();
			board.setFname(fname);
			
			try {
				multipartFile.transferTo(new File(uploadDir, fname));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		dao.insertBoard(board);
		
//		return "redirect:board_list.do";
		return "redirect:board_list";
	}
	
//	@RequestMapping(value="/board_list.do", method=RequestMethod.GET)
	/*@RequestMapping("/board_list.do")*/
	@RequestMapping("/board_list")
	public String board_list(Model model){
		//����Ͻ� ó��
		List<Board> list = dao.listBoard();
		//������ ��������
		model.addAttribute("list", list);

		return "list";
		/*���ϰ��� tiles-definitions ���ϰ��� */
		//�� �̸� ���ϱ�
	}
	
	
	
	/*@RequestMapping("/board_detail.do")*/
	@RequestMapping("/board_detail{seq}")
	/*public String board_detail(@RequestParam("seq") int seq, Model model){*/
	public String board_detail(@PathVariable int seq, Model model){
		model.addAttribute("board", dao.getBoard(seq));
		return "detail";
		/*���ϰ��� tiles-definitions ���ϰ��� */
	}
	
	
//	���� �ٿ�ε� 
	@RequestMapping("board_download")
	public String board_download(@RequestParam("fname") String filename, Model model)throws Exception{
		
		File file = new File(uploadDir, filename);
		model.addAttribute("downloadFile", file);
		
		return "downloadView";
	}
	

	
	
	
/*	@RequestMapping("/detail.do")
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
	}*/
	
	
	
}
