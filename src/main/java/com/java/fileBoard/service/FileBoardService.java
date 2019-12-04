package com.java.fileBoard.service;

import org.springframework.web.servlet.ModelAndView;

public interface FileBoardService {
	public void fileWriter(ModelAndView mav);
	public void fileWriterOk(ModelAndView mav);
	public void fileBoardList(ModelAndView mav);
	public void fileBoardRead(ModelAndView mav);
	public void fileBoardDelete(ModelAndView mav);
	public void fileBoardUpdate(ModelAndView mav);
	public void fileBoardUpdateOk(ModelAndView mav) throws Throwable;
	public void fileBoardDownload(ModelAndView mav);
}
