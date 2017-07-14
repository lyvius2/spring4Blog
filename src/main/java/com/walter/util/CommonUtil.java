package com.walter.util;

import com.walter.model.MemberVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by Walter on 2017-07-03.
 */
@Component
public class CommonUtil {
	public static MemberVO getLoginUserInfo() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		if (principal != null && principal instanceof MemberVO) {
			return (MemberVO) principal;
		} else {
			return new MemberVO();
		}
	}
}
