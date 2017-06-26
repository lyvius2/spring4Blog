package com.walter.service;

import com.walter.model.MemberVO;

/**
 * Created by yhwang131 on 2017-06-26.
 */
public interface SocialService {
	MemberVO bindingSocialUserInfo(String socialType);
}
