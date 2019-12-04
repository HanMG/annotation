package com.java.guest.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.java.aop.HomeAspect;
import com.java.guest.dao.GuestDao;
import com.java.guest.dto.GuestDto;

@Component
public class GuestServiceImp implements GuestService {	
	
	@Autowired
	private GuestDao guestDao;			
	
	@Override
	public void guestWrite(ModelAndView mav) {		
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");		

		String pageNumber = request.getParameter("pageNumber"); // get방식으로 받을 페이지 넘버
		
		if (pageNumber == null) // index에서 첫 클릭시 
			pageNumber = "1";

		int currentPage = Integer.parseInt(pageNumber); // 1) 요청페이지 1
		
		int boardSize = 2; // 2) 페이지당 출력할 게시물 수
		int starRow = (currentPage - 1) * boardSize + 1; // 시작번호
		int endRow = currentPage * boardSize; // 끝번호

		int count = guestDao.getCount(); // 총 데이터 수 
		HomeAspect.logger.info(HomeAspect.logMsg+count);
		List<GuestDto> guestList = null;
		if (count > 0) {
			guestList = guestDao.guestList(starRow, endRow); // 시작번호와 끝번호를 통해 보여질 데이터를 ArrayList로 가져옴
			HomeAspect.logger.info(HomeAspect.logMsg +" 데이터 몇개: "+guestList.size());
		}		
		
		if (guestList != null) {
			request.setAttribute("count", count);
			request.setAttribute("guestList", guestList);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("boardSize", boardSize);			
		}
		mav.addObject("request");
		mav.setViewName("guest/write");
	}

	@Override
	public void guestWriteOk(ModelAndView mav) {		
		Map<String, Object> map = mav.getModelMap();
		GuestDto guestDto = (GuestDto) map.get("guestDto");		
		
		guestDto.setWriteDate(new Date());
		guestDto.setMessage(guestDto.getMessage().replace("\r\n", "<br/>"));
		
		int check = guestDao.insert(guestDto);
		HomeAspect.logger.info(HomeAspect.logMsg+check);
		
		mav.addObject("check",check);
		mav.setViewName("guest/writeOk");
	}

	@Override
	public void guestDelete(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");		
		
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));		
		
		int check = guestDao.delete(num);		
		
		request.setAttribute("pageNumber", pageNumber);	
		
		mav.addObject("check",check);
		mav.setViewName("guest/delete");
		
	}

	@Override
	public void guestUpdate(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int num = Integer.parseInt(request.getParameter("num"));		
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		HomeAspect.logger.info(HomeAspect.logMsg+num+"\t"+pageNumber);
		GuestDto guestDto = guestDao.update(num);
		HomeAspect.logger.info(HomeAspect.logMsg+guestDto.toString());
		
		request.setAttribute("guestDto", guestDto);
		request.setAttribute("pageNumber", pageNumber);
		
		mav.addObject("guestDto",guestDto);
		mav.setViewName("guest/update");		
	}

	@Override
	public void guestUpdateOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		GuestDto guestDto = (GuestDto) map.get("guestDto");	
				
		guestDto.setNum(Integer.parseInt(request.getParameter("num")));
		guestDto.setPassword(request.getParameter("pwd"));
		guestDto.setMessage(request.getParameter("message"));
		HomeAspect.logger.info(HomeAspect.logMsg+guestDto.toString());
		
		int check = guestDao.updateOk(guestDto);
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		
		HomeAspect.logger.info(HomeAspect.logMsg+check);		
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("check", check);
		mav.setViewName("guest/updateOk");
		
	}	
	
}
