package com.walter.config.interceptor;

import com.walter.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 메뉴 표시 용 인터셉터
 * Created by yhwang131 on 2017-06-20.
 */
public class WaltersInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private ConfigService configService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setAttribute("categories", configService.getCategoryList());
		return super.preHandle(request, response, handler);
	}
}
