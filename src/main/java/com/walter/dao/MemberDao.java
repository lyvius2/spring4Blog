package com.walter.dao;

import com.walter.model.MemberVO;
import com.walter.model.RoleVO;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yhwang131 on 2016-08-22.
 */
public interface MemberDao {
	List<MemberVO> getMemberList(String searchText);
	MemberVO getMemberDetail(HashMap<String, Object> param);
	int setMember(MemberVO member);
	List<RoleVO> getRoleList(String username);
	int setRole(RoleVO roleVO);
}
