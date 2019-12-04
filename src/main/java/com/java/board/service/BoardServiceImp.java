package com.java.board.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.java.aop.HomeAspect;
import com.java.board.dao.BoardDao;
import com.java.board.dto.BoardDto;

@Component
public class BoardServiceImp implements BoardService {
	
	@Autowired
	private BoardDao boardDao;	
	
	@Override
	public void boardWrite(ModelAndView mav) {
		// TODO Auto-generated method stub
		// 부모글 ROOT
				int boardNumber =0;		//글번호(DB 결정)
				int groupNumber =1;		//그룹번호
				int sequenceNumber=0;	//글순서
				int sequenceLevel=0;	//글레벨
				
				
			Map<String,Object> map = mav.getModelMap();
			HttpServletRequest request = (HttpServletRequest) map.get("request");
				
				if(request.getParameter("boardNumber") != null) {
					// 답글 
					boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
					groupNumber=Integer.parseInt(request.getParameter("groupNumber"));
					sequenceNumber=Integer.parseInt(request.getParameter("sequenceNumber"));
					sequenceLevel=Integer.parseInt(request.getParameter("sequenceLevel"));
					
				}
//				System.out.println(boardNumber);
//				System.out.println(groupNumber);
//				System.out.println(sequenceNumber);
//				System.out.println(sequenceLevel);
//				
				request.setAttribute("boardNumber", boardNumber);
				request.setAttribute("groupNumber", groupNumber);
				request.setAttribute("sequenceNumber", sequenceNumber);
				request.setAttribute("sequenceLevel", sequenceLevel);		//4개의값 히든
				
				mav.setViewName("board/write"); //이렇게 해도됨 setAtri뷰트로 넘겨줄때
			//	return "/WEB-INF/views/board/write.jsp"; 
	}

	@Override
	public void boardWriteOk(ModelAndView mav) {
		Map<String,Object>map= mav.getModelMap();		
		
		BoardDto boardDto=(BoardDto) map.get("boardDto");
		System.out.println(boardDto.toString());
		HomeAspect.logger.info(HomeAspect.logMsg+boardDto.toString());
		
		boardWriteNumber(boardDto);
		
		
		boardDto.setWriteDate(new Date());
		boardDto.setReadCount(0);
		
		 int check = boardDao.boardWriteOk(boardDto);
		HomeAspect.logger.info(HomeAspect.logMsg+check);
		
		mav.addObject("check",check);
		mav.setViewName("board/writeOk");
	
		
	}
		
	

	public void boardWriteNumber(BoardDto boardDto) {
		// TODO Auto-generated method stub
		int boardNumber = boardDto.getBoardNumber(); // 0
		int groupNumber = boardDto.getGroupNumber(); // 1
		int sequenceNumber = boardDto.getSequenceNumber(); // 0
		int sequenceLevel = boardDto.getSequenceLevel(); // 0
		
		if (boardNumber == 0) { // Root-그룹번호
			int max= boardDao.groupNumberMax();
			HomeAspect.logger.info(HomeAspect.logMsg+max);
			
			if(max !=0 ) boardDto.setGroupNumber(max+1);
		}else {    //답글 - 글레벨 ,글순서	
			Map<String,Integer> map = new HashMap<String, Integer>();
			map.put("groupNumber",groupNumber);
			map.put("sequenceNumber",sequenceNumber);
			
			int check = boardDao.boardWriteNumber(map);
			HomeAspect.logger.info(HomeAspect.logMsg+check);
			
			
			boardDto.setSequenceNumber(sequenceNumber+1);
			boardDto.setSequenceLevel(sequenceLevel+1);
			
		}

	}

	@Override
	public void boardList(ModelAndView mav) {
		
		Map<String,Object>map = mav.getModel();
		HttpServletRequest request =(HttpServletRequest) map.get("request");	//Map방식으로 변환
		
		String pageNumber = request.getParameter("pageNumber");
		if (pageNumber == null)
			pageNumber = "1";

		int currentPage = Integer.parseInt(pageNumber); // 시작 - 끝
		HomeAspect.logger.info(HomeAspect.logMsg+currentPage);
		
		//int count = BoardDao.getInstance().getCount();
		int count = boardDao.boardCount();				//Dao Interface로 이동
		HomeAspect.logger.info(HomeAspect.logMsg+count);
		

		int boardSize = 10;
		int startRow = (currentPage - 1) * boardSize + 1;
		int endRow = currentPage * boardSize;

		List<BoardDto> boardList=null;
		
		
		if (count > 0) {
			
			//boardList=BoardDao.getInstance().getBoardList(startRow,endRow);
			boardList=boardDao.boardList(startRow,endRow);
			HomeAspect.logger.info(HomeAspect.logMsg+boardList.size());
		}
		
		mav.addObject("boardSize", boardSize);
		mav.addObject("currentPage", currentPage);
		mav.addObject("count", count);
		mav.addObject("boardList", boardList);
		
		mav.setViewName("board/list");
	}

	@Override
	public void boardRead(ModelAndView mav) {
		// TODO Auto-generated method stub
		Map<String,Object>map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		HomeAspect.logger.info(HomeAspect.logMsg+boardNumber+","+pageNumber);
		
		BoardDto boardDto = boardDao.boardRead(boardNumber);
		HomeAspect.logger.info(HomeAspect.logMsg+boardDto.toString());
		
		mav.addObject("boardDto",boardDto);
		mav.addObject("pageNumber",pageNumber);
		
		mav.setViewName("board/read");
	}

	@Override
	public void boardDeleteOk(ModelAndView mav) {
		// TODO Auto-generated method stub
		Map<String,Object>map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
		HomeAspect.logger.info(HomeAspect.logMsg+boardNumber);
			
		int check = boardDao.boardDelete(boardNumber);
		
		mav.addObject("check",check);
		
		mav.setViewName("board/delete");
	}

	@Override
	public void boardUpdate(ModelAndView mav) {
		// TODO Auto-generated method stub
		Map<String,Object>map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		HomeAspect.logger.info(HomeAspect.logMsg+boardNumber+","+pageNumber);
		
		BoardDto boardDto = boardDao.boardUpRead(boardNumber);
		HomeAspect.logger.info(HomeAspect.logMsg+boardDto.toString());
		
		mav.addObject("boardDto",boardDto);
		mav.addObject("pageNumber",pageNumber);
		
		mav.setViewName("board/update");
		
		
	}

	@Override
	public void boardUpdateOk(ModelAndView mav) {
		// TODO Auto-generated method stub
		Map<String,Object>map = mav.getModelMap();		
		BoardDto boardDto = (BoardDto) map.get("boardDto");
		
		// int pageNumber=Integer.parseInt(request.getParameter("pageNumber")); 
		HomeAspect.logger.info(HomeAspect.logMsg+boardDto.toString());
		int check = boardDao.boardUpdateOk(boardDto);
		HomeAspect.logger.info(HomeAspect.logMsg+check);
		
		
		mav.addObject("check",check);
		mav.setViewName("board/updateOk");
	}
		
	
	
}



