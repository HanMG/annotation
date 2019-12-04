package com.java.member.dao;


import java.util.List;

import com.java.member.dto.MemberDto;
import com.java.member.dto.ZipcodeDto;

public interface MemberDao {	

	public int signInOk(MemberDto memberDto);

	public String idCheck(String id);

	public List<ZipcodeDto> zipcodeReader(String checkDong);

	public String login(String id, String pwd);

	public MemberDto update(String id);

	public int updateOk(MemberDto memberDto);

	public int dropoutOk(MemberDto memberDto);
	

	

}
