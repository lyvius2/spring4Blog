package com.yunson.firstapp.member;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yhwang131 on 2016-08-22.
 */
public interface MemberDao {
	List<MemberVO> getMemberList(String searchText);
	MemberVO getMemberDetail(HashMap<String, Object> param);
	int setMember(MemberVO member);
}
