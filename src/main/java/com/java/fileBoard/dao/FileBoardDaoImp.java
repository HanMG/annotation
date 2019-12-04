package com.java.fileBoard.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.fileBoard.dto.FileBoardDto;

@Component
public class FileBoardDaoImp implements FileBoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int groupNumberMax() {
		return sqlSessionTemplate.selectOne("dao.FileBoardMapper.fileBoardGroupNumberMax");
	}

	@Override
	public int fileInsertAll(FileBoardDto fileBoardDto) {
		int check = 0;

		if (fileBoardDto.getFileName() != null) {
			check = sqlSessionTemplate.insert("fileBoardInsertAll", fileBoardDto);
		} else {
			check = sqlSessionTemplate.insert("fileBoardInsertBasic", fileBoardDto);
		}
		return check;
	}

	@Override
	public int getCount() {

		return sqlSessionTemplate.selectOne("dao.FileBoardMapper.fileBoardCount");
	}

	@Override
	public List<FileBoardDto> getBoardList(int startRow, int endRow) {
		Map<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("startRow", startRow);
		hMap.put("endRow", endRow);
		return sqlSessionTemplate.selectList("fileBoardList", hMap);
	}

	@Override
	public FileBoardDto read(int boardNumber) {
		sqlSessionTemplate.update("fileBoardViews", boardNumber);

		return sqlSessionTemplate.selectOne("dao.FileBoardMapper.fileBoardRead", boardNumber);
	}

	@Override
	public FileBoardDto selectBoard(int boardNumber) {
		return sqlSessionTemplate.selectOne("dao.FileBoardMapper.fileBoardSelect", boardNumber);
	}

	@Override
	public int delBoard(int boardNumber) {
		return sqlSessionTemplate.delete("dao.FileBoardMapper.fileBoardDelete", boardNumber);
	}

	@Override
	public int boardWriteNumber(Map<String, Integer> map) {	
		return sqlSessionTemplate.update("dao.FileBoardMapper.fileBoardWriteNumber",map);
	}

	@Override
	public int fileBoardUpdateAll(FileBoardDto fileBoardDto) {
		int check = 0;

		if (fileBoardDto.getFileName() != null) {
			check = sqlSessionTemplate.update("dao.FileBoardMapper.fileBoardUpdateAll", fileBoardDto);
		} else {
			check = sqlSessionTemplate.update("dao.FileBoardMapper.fileBoardUpdateBasic", fileBoardDto);
		}
		return check;
	}

}
