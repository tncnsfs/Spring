package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.service.HelloService;

@Controller //��Ʈ�ѷ��� ��Ʈ�ѷ� ������̼� �ʿ�
public class HelloController {
	
	private HelloService  service;
	
		@Autowired//sertter�� di property���
		public void setService(HelloService service) {
			this.service = service;
		}
	
	
	@RequestMapping("/client2")
	public String boardJson(){
		return "client2";
	}
		
	@RequestMapping("/client")
	public String client(){
		return "client";
	}
	
	
	
	@RequestMapping("/hello.do") //������̼����� ����
	public ModelAndView hello() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("hello");  // view ������ jsp�� ���� 
		mav.addObject("massage", service.getMessage()); // data ���� ���� 
		
		
		return mav;
	}
	
	
}
