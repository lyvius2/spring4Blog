package com.walter.controller;

import com.google.gson.Gson;
import com.walter.model.MemberVO;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

/**
 * Created by yhwang131 on 2016-10-04.
 */
@Controller
public class BaseController {

	@Autowired
	protected Gson gson;

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	protected final MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	@Nullable
	protected MemberVO getLoginUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		if (principal != null && principal instanceof MemberVO) {
			return (MemberVO) principal;
		} else {
			return null;
		}
	}
}
