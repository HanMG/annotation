package com.java.board.dao;

import java.util.List;
import java.util.Map;

import com.java.board.dto.BoardDto;

public interface BoardDao {
	
	public int groupNumberMax();
	public int boardWriteOk(BoardDto boardDto);
	public int boardWriteNumber(Map<String, Integer> map);
	public int boardCount();
	public List<BoardDto> boardList(int startRow,int endRow);
	public BoardDto boardRead(int boardNumber);
	public int boardDelete(int boardNumber);
	public BoardDto boardUpRead(int boardNumber);
	public int boardUpdateOk(BoardDto boardDto);
	
}
