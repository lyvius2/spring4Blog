package com.walter.dao;

import com.walter.model.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yhwang131 on 2016-08-22.
 */
@Component
@Mapper
public interface MemberDao {
	int insMember(MemberVO memberVO);
	int modMember(MemberVO memberVO);
	MemberVO getMember(HashMap<String, Object> param);
	List<MemberVO> getMemberList();
}
