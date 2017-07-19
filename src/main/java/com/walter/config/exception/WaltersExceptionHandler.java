package com.walter.config.exception;

import com.walter.model.ExceptionVO;
import com.walter.model.MemberVO;
import com.walter.service.LogService;
import com.walter.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * Created by yhwang131 on 2017-07-14.
 */
@Slf4j
@ControllerAdvice
public class WaltersExceptionHandler {

	@Autowired
	private LogService logService;

	@ExceptionHandler(Exception.class)
	public void handleException(Exception e, HttpServletRequest request) {
		HashMap<String, Object> hashMap = new HashMap<>();
		Enumeration params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String param = params.nextElement().toString();
			hashMap.put(param, request.getParameter(param));
		}
		saveExceptionLog(e, request.getServletPath(), request.getRemoteAddr(), request.getMethod(), hashMap);
	}

	private void saveExceptionLog(Exception e, String request_path, String ip, String httpMethodName,
	                              HashMap<String, Object> params) {
		ExceptionVO exceptionVO = new ExceptionVO();
		exceptionVO.setException(e.getClass().getSimpleName());
		exceptionVO.setMessage(e.toString());
		exceptionVO.setRequest_path(request_path);
		exceptionVO.setIp(ip);
		exceptionVO.setMethod(httpMethodName);
		exceptionVO.setParams(params.toString());
		exceptionVO.setTrace_log(ExceptionUtils.getStackTrace(e));

		MemberVO memberVO = CommonUtil.getLoginUserInfo();
		exceptionVO.setUsername(memberVO.getUsername());
		exceptionVO.setUserlink(memberVO.getLink());
		logService.setException(exceptionVO);
	}
}
