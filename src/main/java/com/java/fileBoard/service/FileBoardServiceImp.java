package com.java.fileBoard.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.java.aop.HomeAspect;
import com.java.fileBoard.dao.FileBoardDao;
import com.java.fileBoard.dto.FileBoardDto;


@Component
public class FileBoardServiceImp implements FileBoardService {
	@Autowired
	private FileBoardDao fileBoardDao;
	
	@Override
	public void fileWriter(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		// 부모글 , ROOT글
		int boardNumber = 0; // 글번호 (DB 결정)
		int groupNumber = 1; // 그룹번호
		int sequenceNumber = 0; // 글순서
		int sequenceLevel = 0; // 글레벨

		if (request.getParameter("boardNumber") != null) {
			// 답글
			boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
			groupNumber = Integer.parseInt(request.getParameter("groupNumber"));
			sequenceNumber = Integer.parseInt(request.getParameter("sequenceNumber"));
			sequenceLevel = Integer.parseInt(request.getParameter("sequenceLevel"));
		}

		request.setAttribute("boardNumber", boardNumber);
		request.setAttribute("groupNumber", groupNumber);
		request.setAttribute("sequenceNumber", sequenceNumber);
		request.setAttribute("sequenceLevel", sequenceLevel);

		mav.setViewName("fileBoard/write");
	}

	@Override
	public void fileWriterOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		FileBoardDto fileBoardDto = (FileBoardDto) map.get("fileBoardDto");
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) map.get("request");

		fileBoardWriteNumber(fileBoardDto);
		fileBoardDto.setWriteDate(new Date());
		fileBoardDto.setReadCount(0);

		MultipartFile upFile = request.getFile("file"); // write.jsp의 input type file의 name으로 확인
		long fileSize = upFile.getSize();
		int check = 0;
		if (fileSize != 0) {
			String fileName = Long.toString(System.currentTimeMillis()) + "_" + upFile.getOriginalFilename();

			File path = new File("C:\\ftp\\");
			path.mkdir();

			if (path.exists() && path.isDirectory()) {
				File file = new File(path, fileName);
				try {
					upFile.transferTo(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
				fileBoardDto.setPath(file.getAbsolutePath());
				fileBoardDto.setFileSize(fileSize);
				fileBoardDto.setFileName(fileName);
			}
		}
		HomeAspect.logger.info(HomeAspect.logMsg + fileBoardDto.toString());
		check = fileBoardDao.fileInsertAll(fileBoardDto);
		HomeAspect.logger.info(HomeAspect.logMsg + check);
		mav.addObject("check", check);
		mav.setViewName("fileBoard/writeOk");
	}

	public void fileBoardWriteNumber(FileBoardDto fileBoardDto) {
		// TODO Auto-generated method stub
		int boardNumber = fileBoardDto.getBoardNumber(); // 0
		int groupNumber = fileBoardDto.getGroupNumber(); // 1
		int sequenceNumber = fileBoardDto.getSequenceNumber(); // 0
		int sequenceLevel = fileBoardDto.getSequenceLevel(); // 0

		if (boardNumber == 0) { // Root-그룹번호
			int max = fileBoardDao.groupNumberMax();
			HomeAspect.logger.info(HomeAspect.logMsg + max);

			if (max != 0)
				fileBoardDto.setGroupNumber(max + 1);
		} else { // 답글 - 글레벨 ,글순서
			
			  Map<String,Integer> map = new HashMap<String, Integer>();
			  map.put("groupNumber",groupNumber);
			  map.put("sequenceNumber",sequenceNumber);
			  
			  int check = fileBoardDao.boardWriteNumber(map);
			  HomeAspect.logger.info(HomeAspect.logMsg+check);
			  
			  
			  fileBoardDto.setSequenceNumber(sequenceNumber+1);
			  fileBoardDto.setSequenceLevel(sequenceLevel+1);
			 
		}

	}

	@Override
	public void fileBoardList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String pageNumber = request.getParameter("pageNumber");

		if (pageNumber == null) {
			pageNumber = "1";
		}

		int currentPage = Integer.parseInt(pageNumber); // 요청페이지

		int count = fileBoardDao.getCount(); // 총 데이터수
		HomeAspect.logger.info(HomeAspect.logMsg + count);

		int boardSize = 10; // 출력할 데이터수
		int startRow = (currentPage - 1) * boardSize + 1; // 시작점
		int endRow = currentPage * boardSize; // 끝점

		List<FileBoardDto> boardList = null;
		if (count > 0) {
			boardList = fileBoardDao.getBoardList(startRow, endRow);
			HomeAspect.logger.info(HomeAspect.logMsg + boardList.size());
		}

		request.setAttribute("currentPage", currentPage);
		request.setAttribute("boardSize", boardSize);
		request.setAttribute("count", count);
		request.setAttribute("boardList", boardList);

		mav.setViewName("fileBoard/list");
	}

	@Override
	public void fileBoardRead(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		HomeAspect.logger.info(HomeAspect.logMsg + boardNumber + "\t" + pageNumber);

		FileBoardDto fileBoardDto = fileBoardDao.read(boardNumber);

		if (fileBoardDto.getFileName() != null) {
			int index = fileBoardDto.getFileName().indexOf("_") + 1;
			fileBoardDto.setFileName(fileBoardDto.getFileName().substring(index));
		}
		HomeAspect.logger.info(HomeAspect.logMsg + fileBoardDto.toString());
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("boardDto", fileBoardDto);

		mav.setViewName("fileBoard/read");

	}

	@Override
	public void fileBoardDelete(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));

		FileBoardDto readBoard = fileBoardDao.selectBoard(boardNumber);
		int check = fileBoardDao.delBoard(boardNumber);

		HomeAspect.logger.info(HomeAspect.logMsg + readBoard.getFileName());

		if (check > 0 && readBoard.getFileName() != null) {
			File file = new File(readBoard.getPath());
			if (file.exists() && file.isFile()) {
				file.delete();
			}
		}

		HomeAspect.logger.info(HomeAspect.logMsg + check);
		request.setAttribute("check", check);
		request.setAttribute("pageNumber", pageNumber);

		mav.setViewName("fileBoard/delete");

	}

	@Override
	public void fileBoardUpdate(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));

		FileBoardDto fileBoardDto = fileBoardDao.selectBoard(boardNumber);

		if (fileBoardDto.getFileName() != null) {
			int index = fileBoardDto.getFileName().indexOf("_") + 1;
			fileBoardDto.setFileName(fileBoardDto.getFileName().substring(index));
		}

		if (fileBoardDto != null) {
			request.setAttribute("boardDto", fileBoardDto);
			request.setAttribute("boardNumber", boardNumber);
			request.setAttribute("pageNumber", pageNumber);
		}

		mav.setViewName("fileBoard/update");

	}

	@Override
	public void fileBoardUpdateOk(ModelAndView mav){

		Map<String, Object> map = mav.getModelMap();
		FileBoardDto fileBoardDto = (FileBoardDto) map.get("fileBoardDto");
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) map.get("request");
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));	
		
		MultipartFile upFile = request.getFile("file"); // write.jsp의 input type file의 name으로 확인
		long fileSize = upFile.getSize();
		int check = 0;
		if (fileSize != 0) {
			String fileName = Long.toString(System.currentTimeMillis()) + "_" + upFile.getOriginalFilename();

			File path = new File("C:\\ftp\\");
			path.mkdir();

			if (path.exists() && path.isDirectory()) {
				File file = new File(path, fileName);
				try {
					upFile.transferTo(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
				fileBoardDto.setPath(file.getAbsolutePath());
				fileBoardDto.setFileSize(fileSize);
				fileBoardDto.setFileName(fileName);
			}
			FileBoardDto readBoard = fileBoardDao.selectBoard(boardNumber);

			if (readBoard.getFileName() != null) {
				File checkFile = new File(readBoard.getPath());
				if (checkFile.exists() && checkFile.isFile()) {
					checkFile.delete();
				}
			}
		}
		fileBoardDto.setWriteDate(new Date());
		HomeAspect.logger.info(HomeAspect.logMsg + fileBoardDto.toString());
		check = fileBoardDao.fileBoardUpdateAll(fileBoardDto);
		
		HomeAspect.logger.info(HomeAspect.logMsg + check);
		
		mav.addObject("check", check);
		
		request.setAttribute("boardNumber", boardNumber);
		request.setAttribute("pageNumber", pageNumber);

		mav.setViewName("fileBoard/updateOk");
	}

	@Override
	public void fileBoardDownload(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpServletResponse response = (HttpServletResponse) map.get("response");

		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		HomeAspect.logger.info(HomeAspect.logMsg + boardNumber);

		FileBoardDto fileBoardDto = fileBoardDao.selectBoard(boardNumber);
		HomeAspect.logger.info(HomeAspect.logMsg + fileBoardDto.toString());
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			int index = fileBoardDto.getFileName().indexOf("_") + 1;
			String dbName = fileBoardDto.getFileName().substring(index);
			String fileName = new String(dbName.getBytes("utf-8"), "ISO-8859-1");

			// 대화창
			// response.setHeader("Content-Disposition", "attachment;filename="+
			// URLEncoder.encode(dbName, "UTF-8"));
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setContentType("application/octet-stream");
			response.setContentLength((int) fileBoardDto.getFileSize());

			// 다운로드

			bis = new BufferedInputStream(new FileInputStream(fileBoardDto.getPath()));
			bos = new BufferedOutputStream(response.getOutputStream());

			while (true) {
				int data = bis.read();
				if (data == -1)
					break;
				bos.write(data);
			}

			bos.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
			if (bos != null)
				bos.close();
			if (bis != null)
				bis.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}

	}

}
