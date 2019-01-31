package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class SessionAspect {

	
	// �ڵ����� ����üũ�� �ϰڴٰ� ���� -> ���� ��Ű���ȿ� 
	@Around("execution(public * com.controller.*.*exe(..))")
	// SessionController.java . reqeust �Ķ���͸� �ҷ��� ProceedingJoinPoint
	public String sessionCheck(ProceedingJoinPoint joinPoint)throws Throwable{
		// �迭 �������� �Ķ���͸� ������ object ������� 
		Object[] obj =  joinPoint.getArgs();
		HttpServletRequest request = (HttpServletRequest)obj[0];
		
		// request ���� getSession �ϸ� session �� ���ü� ���� 
		HttpSession session = request.getSession();
		// session , name �̶�� �̸����� ���� ������ �ִ°� �ҷ����� 
		String name = (String)session.getAttribute("name");
		
		String view = "session/session_fail";
		
		try {
			// ������������ if �� �پ�ѱ� 
			if(name == null){
				throw new Exception("no Session");
			}
			
			// proceed()ȣ���� ,, aspect �� ������ɻ����� ����뼭 ���� ȣ��ȵ�,,, 
			// ����  around ���� ,,
			// session_exe()�޼ҵ� ȣ��� ����
			// success.jsp �� ���ϵ� 
			view = (String)joinPoint.proceed();  
			// around �� �ٽɻ����� �������� ����/���� ���� @Around
			
		} catch (Exception e) {
			// ���н� session_fail.jsp 
			return view; 
		}
		// ������ session_success.jsp 
		return view;
	}
	
}




