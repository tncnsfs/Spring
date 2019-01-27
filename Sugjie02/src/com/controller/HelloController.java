package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.model.HelloService;

@Controller //��Ʈ�ѷ��� ��Ʈ�ѷ� ������̼� �ʿ�
public class HelloController {
	
	private HelloService  service;
	
		@Autowired//sertter�� di property���
		public void setService(HelloService service) {
			this.service = service;
		}
	
	@RequestMapping("/hello.do") //������̼����� ����
	public ModelAndView hello() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("hello");  // view ������ jsp�� ���� 
		mav.addObject("massage", service.getMessage()); // data ���� ���� 
		
		
		return mav;
	}
	
	
}
