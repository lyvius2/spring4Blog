package com.walter.controller;

import com.google.gson.Gson;
import com.walter.model.MemberVO;
import com.walter.util.CommonUtil;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yhwang131 on 2016-10-04.
 */
@Controller
public class BaseController {
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	protected final MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	@Autowired
	protected Gson gson;

	@Nullable
	protected MemberVO getLoginUser() {
		return CommonUtil.getLoginUserInfo();
	}

	protected String getUsername() {
		MemberVO memberVO = this.getLoginUser();
		return memberVO != null ? memberVO.getUsername() : "Anonymous";
	}

	protected <T> ResponseEntity createResEntity(T body) {
		return new ResponseEntity(body, HttpStatus.OK);
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		simpleDateFormat.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
	}
}
