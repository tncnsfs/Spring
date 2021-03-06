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
	
	public BoardDao getDao() {
		return dao;
	}
	
	@Autowired
	public void setDao(BoardDao dao) {
		this.dao = dao;
	}
	
	
	/*@RequestMapping(value="/board_insert.do", method=RequestMethod.GET)*/
	@RequestMapping(value="/board_insert", method=RequestMethod.GET)
	public String insertform(@ModelAttribute("boardCommand") Board board, Model model){ 
		//데이터타입이 string이라면 , Model에서 받는다. 즉, 데이터 받는방법 데이터타입2가지 : modelandview, string-model
		model.addAttribute("title", "글쓰기2");
		
		return "insert_form";/*리턴값은 tiles-definitions 리턴값임 */
	}

	/*@RequestMapping(value="/board_insert.do", method=RequestMethod.POST)*/
	@RequestMapping(value="/board_insert", method=RequestMethod.POST)
	public String board_insert(@ModelAttribute("boardCommand") @Valid Board board, BindingResult errors){ 
		
		if(errors.hasErrors()) //바인딩 객체에 에러가 있냐
		{
			System.out.println("에러 발생");
			return "insert_form";
			/*리턴값은 tiles-definitions 리턴값임 */
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
	
	@RequestMapping("/board_list")
	public String board_list(Model model){
		//비즈니스 처리
		List<Board> list = dao.listBoard();
		//데이터 가져오기
		model.addAttribute("list", list);

		return "list";
		/*리턴값은 tiles-definitions 리턴값임 */
		//뷰 이름 정하기
	}
	
	
	
	@RequestMapping("/board_detail{seq}")
	public String board_detail(@PathVariable int seq, Model model){
		model.addAttribute("board", dao.getBoard(seq));
		return "detail";
		/*리턴값은 tiles-definitions 리턴값임 */
	}
	
	
//	파일 다운로드 
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
		System.out.println("seq를: " + seq);
		
		mv.addObject("seq", board.getSeq());
		mv.addObject("title", board.getTitle());
		mv.addObject("writer", board.getWriter());
		mv.addObject("contents", board.getContents());
		System.out.println("board.getSeq: " + board.getSeq());
		return mv;
	}*/
	
	/*@InitBinder
	protected void InitBinder(WebDataBinder binder){
		binder.setValidator(new BoardValidator());
	}*/

	
}
