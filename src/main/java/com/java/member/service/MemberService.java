package com.java.member.service;

import org.springframework.web.servlet.ModelAndView;



public interface MemberService {
	
	public void signIn(ModelAndView mav);
	
	public void signInOk(ModelAndView mav);

	public void zipcodeReader(ModelAndView mav);

	public void idCheck(ModelAndView mav);

	public void loginOk(ModelAndView mav);

	public void update(ModelAndView mav);

	public void updateOk(ModelAndView mav);

	public void dropOutOk(ModelAndView mav);

	

	
	

}
