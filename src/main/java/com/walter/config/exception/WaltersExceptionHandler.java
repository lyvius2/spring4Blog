package com.walter.config.exception;

import com.walter.dao.LogDao;
import com.walter.model.MemberVO;
import com.walter.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
	private LogDao logDao;

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
		HashMap<String, Object> inputMap = new HashMap<>();
		inputMap.put("exception", e.getClass().getSimpleName());
		inputMap.put("message", e.toString());
		inputMap.put("request_path", request_path);
		inputMap.put("ip", ip);
		inputMap.put("method", httpMethodName);
		inputMap.put("params", params.toString());
		inputMap.put("trace_log", ExceptionUtils.getStackTrace(e));

		MemberVO memberVO = CommonUtil.getLoginUserInfo();
		inputMap.put("username", memberVO.getUsername());
		inputMap.put("userlink", memberVO.getLink());
		logDao.insExceptionLog(inputMap);
	}
}
