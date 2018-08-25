package com.walter.service;

import com.walter.model.MemberVO;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * Created by yhwang131 on 2017-06-26.
 */
public interface SocialService {
	MemberVO bindingSocialUserInfo(String socialType, NativeWebRequest request);
}
