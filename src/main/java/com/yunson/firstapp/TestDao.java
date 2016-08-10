package com.yunson.firstapp;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by yhwang131 on 2016-08-04.
 */
public interface TestDao {
	String getTest() throws SQLException;
	List<MemberVO> getList();
	MemberVO getMember(MemberVO vo);
}
