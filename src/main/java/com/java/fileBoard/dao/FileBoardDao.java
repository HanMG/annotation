package com.java.fileBoard.dao;

import java.util.List;
import java.util.Map;

import com.java.fileBoard.dto.FileBoardDto;

public interface FileBoardDao {
	public int groupNumberMax();

	public int fileInsertAll(FileBoardDto fileBoardDto);

	public int getCount();

	public List<FileBoardDto> getBoardList(int startRow, int endRow);

	public FileBoardDto read(int boardNumber);

	public FileBoardDto selectBoard(int boardNumber);

	public int delBoard(int boardNumber);
	
	public int boardWriteNumber(Map<String, Integer> map);

	public int fileBoardUpdateAll(FileBoardDto fileBoardDto);	
}
