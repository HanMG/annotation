package com.java.guest.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.guest.dto.GuestDto;

@Component
public class GuestDaoImp implements GuestDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;		

	@Override
	public int insert(GuestDto guestDto) {		
		return sqlSessionTemplate.insert("guestIns", guestDto);
	}

	@Override
	public int getCount() {
		return sqlSessionTemplate.selectOne("guestTotal");
	}

	@Override
	public List<GuestDto> guestList(int startRow, int endRow) {
		Map<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("startRow", startRow);
		hMap.put("endRow", endRow);
 		return sqlSessionTemplate.selectList("dao.GuestMapper.guestRead",hMap);
	}

	@Override
	public int delete(int num) {		
		return sqlSessionTemplate.delete("dao.GuestMapper.guestDelete", num);
	}

	@Override
	public GuestDto update(int num) {		
		return sqlSessionTemplate.selectOne("dao.GuestMapper.guestSelect",num);
	}

	@Override
	public int updateOk(GuestDto guestDto) {		
		return sqlSessionTemplate.update("dao.GuestMapper.guestUpdate",guestDto);
	}
	
	

	
}
