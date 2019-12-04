package com.java.member.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.java.aop.HomeAspect;
import com.java.member.dao.MemberDao;
import com.java.member.dto.MemberDto;
import com.java.member.dto.ZipcodeDto;
@Component
public class MemberServiceImp implements MemberService {
	@Autowired
	private MemberDao memberDao;

	@Override
	public void signIn(ModelAndView mav) {
		mav.setViewName("member/register");
	}

	@Override
	public void signInOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		MemberDto memberDto = (MemberDto) map.get("memberDto");
		int check = memberDao.signInOk(memberDto);
		mav.addObject("check", check);
		mav.setViewName("member/registerOk");
	}

	

	@Override
	public void idCheck(ModelAndView mav) {
		Map<String,Object>map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int check = 0;
		String id = request.getParameter("id");
		String checkId = memberDao.idCheck(id);
		if (checkId != null) {
			check = 1;
		}
		mav.addObject("id",id);
		mav.addObject("check",check);
		mav.setViewName("member/idCheck");
	}

	@Override
	public void zipcodeReader(ModelAndView mav) {
		Map<String,Object>map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String checkDong= request.getParameter("dong");
		if(checkDong != null) {
			List<ZipcodeDto> zipcodeList =	memberDao.zipcodeReader(checkDong);
			HomeAspect.logger.info(HomeAspect.logMsg + zipcodeList.size());
			mav.addObject("zipcodeList", zipcodeList);
			mav.setViewName("member/zipcode");
		}		
		
	}

	@Override
	public void loginOk(ModelAndView mav) {
		Map<String,Object>map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		HomeAspect.logger.info(HomeAspect.logMsg+id+" "+pwd);
		
		String memberLevel = memberDao.login(id,pwd);
		HomeAspect.logger.info(HomeAspect.logMsg+memberLevel);
		
		if(memberLevel != null) {
			request.setAttribute("id", id);
			request.setAttribute("memberLevel", memberLevel);
		}
		
		mav.setViewName("member/loginOk");
		
	}

	@Override
	public void update(ModelAndView mav) {
		Map<String,Object>map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		HomeAspect.logger.info(HomeAspect.logMsg+id);
		
		MemberDto memberDto = memberDao.update(id);
		HomeAspect.logger.info(HomeAspect.logMsg+memberDto.toString());
		
		request.setAttribute("memberDto", memberDto);
		mav.setViewName("member/update");
		
	}

	@Override
	public void updateOk(ModelAndView mav) {
		Map<String,Object>map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");		
		MemberDto memberDto = (MemberDto) map.get("memberDto");	
		
		HomeAspect.logger.info(HomeAspect.logMsg+memberDto.toString());
		
		int check = memberDao.updateOk(memberDto);
		HomeAspect.logger.info(HomeAspect.logMsg+check);
		
		request.setAttribute("check", check);		
		mav.setViewName("member/updateOk");
		
	}

	@Override
	public void dropOutOk(ModelAndView mav) {
		Map<String,Object>map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
				
		MemberDto memberDto = new MemberDto();
		memberDto.setNum(Integer.parseInt(request.getParameter("num")));
		memberDto.setId(request.getParameter("id"));
		memberDto.setPassword(request.getParameter("pwd"));
		
		int check = memberDao.dropoutOk(memberDto);
		
		request.setAttribute("check", check);
		mav.setViewName("member/dropOutOk");
	}

	

}
