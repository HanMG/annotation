package com.java.member.controller;



import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.member.dto.MemberDto;

import com.java.member.service.MemberService;
@Controller
public class MemberController{
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/member/signIn.do",method = RequestMethod.GET)
	public ModelAndView signIn(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView();
		memberService.signIn(mav);		
		return mav;
	}
	@RequestMapping(value = "/member/signInOk.do",method = RequestMethod.POST)
	public ModelAndView signInOk(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto) {
		
		ModelAndView mav = new ModelAndView();	
		memberDto.setMemberLevel("1");
		memberDto.setRegisterDate(new Date());
		System.out.println(memberDto.toString());
		mav.addObject("memberDto",memberDto);
		memberService.signInOk(mav);		
		return mav;
		
	}
	@RequestMapping(value = "/member/zipcode.do",method = RequestMethod.GET)
	public ModelAndView zipcode(HttpServletRequest request, HttpServletResponse response) {		
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		memberService.zipcodeReader(mav);				
		return mav;
	}
	@RequestMapping(value = "/member/idCheck.do",method = RequestMethod.GET)
	public ModelAndView idCheck(HttpServletRequest request, HttpServletResponse response) {		
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);		
		memberService.idCheck(mav);				
		return mav;
	}
	
	@RequestMapping(value="/member/login.do",method=RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/login");	
		return mav;
	}
	
	@RequestMapping(value="/member/loginOk.do",method=RequestMethod.POST)
	public ModelAndView loginOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		memberService.loginOk(mav);
		return mav;	
	}
	@RequestMapping(value="member/logout.do",method=RequestMethod.GET)
	public ModelAndView logOut(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/logout");		
		return mav;	
	}
	@RequestMapping(value="member/update.do",method=RequestMethod.GET)
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		memberService.update(mav);
		return mav;	
	}
	@RequestMapping(value="member/updateOk.do",method=RequestMethod.POST)
	public ModelAndView updateOk(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		mav.addObject("memberDto",memberDto);
		memberService.updateOk(mav);
		return mav;	
	}
	@RequestMapping(value="member/dropOut.do",method=RequestMethod.GET)
	public ModelAndView dropOut(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/droupOut");		
		return mav;	
	}
	@RequestMapping(value="member/dropOutOk.do",method=RequestMethod.POST)
	public ModelAndView dropOutOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		memberService.dropOutOk(mav);
		return mav;	
	}

}
