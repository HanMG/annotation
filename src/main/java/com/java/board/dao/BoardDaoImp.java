package com.java.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.java.aop.HomeAspect;
import com.java.board.dto.BoardDto;

@Component
public class BoardDaoImp implements BoardDao {
	@Autowired
	private SqlSessionTemplate sqlSession;	

	@Override
	public int groupNumberMax() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("boardGroupNumberMax");
	}

	@Override
	public int boardWriteOk(BoardDto boardDto) {
		// TODO Auto-generated method stub
		return sqlSession.insert("dao.BoardMapper.boardInsert",boardDto); //namespace로 써도됨
	}

	@Override
	public int boardWriteNumber(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return sqlSession.update("dao.BoardMapper.boardWriteNumber",map);
	}

	@Override
	public int boardCount() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("dao.BoardMapper.boardCount");
	}

	@Override
	public List<BoardDto> boardList(int startRow, int endRow) {
		// TODO Auto-generated method stub
		Map<String, Integer> hMap =new HashMap<String,Integer>();
		hMap.put("startRow", startRow);
		hMap.put("endRow", endRow);
		return sqlSession.selectList("dao.BoardMapper.boardList",hMap);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public BoardDto boardRead(int boardNumber) {
		// TODO Auto-generated method stub
		BoardDto boardDto = null;
		//트랜잭션 처리 Mapper에서 boardRead쿼리문 에러내보고 DB에 조회수가 증가되는지 확인
	//조회수 증가
		int check = sqlSession.update("dao.BoardMapper.updateread",boardNumber);
		HomeAspect.logger.info(HomeAspect.logMsg+check);
	//해당 글번호 선택
		boardDto=sqlSession.selectOne("dao.BoardMapper.boardread",boardNumber);
		
		return boardDto;
	}

	@Override
	public int boardDelete(int boardNumber) {
		// TODO Auto-generated method stub
		/*
		 * Map<String, Object> hMap =new HashMap<String,Integer>(); hMap.put("startRow",
		 * startRow); hMap.put("endRow", endRow);
		 */
		return sqlSession.delete("dao.BoardMapper.BoardMapperDelete",boardNumber);
	}

	@Override
	
	public BoardDto boardUpRead(int boardNumber) {
		// TODO Auto-generated method stub
		
		return sqlSession.selectOne("dao.BoardMapper.boardread",boardNumber);
	}

	@Override
	public int boardUpdateOk(BoardDto boardDto) {
		// TODO Auto-generated method stub
		
		return sqlSession.update("dao.BoardMapper.BoardMapperUpdata",boardDto);
	}	
	
}
