package com.java.member.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.member.dto.MemberDto;
import com.java.member.dto.ZipcodeDto;

@Component
public class MemberDaoImp implements MemberDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int signInOk(MemberDto memberDto) {
		return sqlSessionTemplate.insert("dao.MemberMapper.insert", memberDto);
	}

	@Override
	public String idCheck(String id) {
		return sqlSessionTemplate.selectOne("dao.MemberMapper.idCheck", id);
	}

	@Override
	public List<ZipcodeDto> zipcodeReader(String checkDong) {

		return sqlSessionTemplate.selectList("dao.MemberMapper.zipcode", checkDong);
	}

	@Override
	public String login(String id, String pwd) {		
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pwd", pwd);	
		return sqlSessionTemplate.selectOne("dao.MemberMapper.login", map);
	}

	@Override
	public MemberDto update(String id) {		 
		return sqlSessionTemplate.selectOne("dao.MemberMapper.select", id);
	}

	@Override
	public int updateOk(MemberDto memberDto) {	
		return sqlSessionTemplate.update("dao.MemberMapper.memberUp",memberDto);
	}

	@Override
	public int dropoutOk(MemberDto memberDto) {		
		return sqlSessionTemplate.delete("dao.MemberMapper.memberDel", memberDto);
	}

}
