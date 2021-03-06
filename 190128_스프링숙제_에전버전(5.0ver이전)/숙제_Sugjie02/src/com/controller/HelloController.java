package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.service.HelloService;

@Controller //컨트롤러는 컨트롤러 어노테이션 필요
public class HelloController {
	
	private HelloService  service;
	
		@Autowired//sertter는 di property방식
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
	
	
	
	@RequestMapping("/hello.do") //어노테이션으로 매핑
	public ModelAndView hello() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("hello");  // view 폴더의 jsp를 전달 
		mav.addObject("massage", service.getMessage()); // data 값을 전달 
		
		
		return mav;
	}
	
	
}
