package com.walter.config;

import com.google.gson.JsonObject;
import com.walter.model.MemberVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by yhwang131 on 2016-08-29.
 */
public class SignInSuccessHandler implements AuthenticationSuccessHandler {

	public static final Logger logger = LoggerFactory.getLogger(SignInSuccessHandler.class);
	private boolean refererUse;
	private String defaultUrl;
	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		/*
		MemberVO memberVO = (MemberVO)authentication.getPrincipal();
		logger.info(new Date() + " - " + memberVO.getUsername() + " 사용자 로그인");
		request.getSession().setAttribute("userInfo", memberVO);
		redirectStrategy.sendRedirect(request, response, connectionRouteDecision(request, response));
		//response.sendRedirect(request.getContextPath() + connectionRouteDecision(request, response));
		*/

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("authenticated", true);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(jsonObject.toString());
	}

	/**
	 * 로그인 성공 후 Redirect URL 결정
	 * @param request
	 * @param response
	 * @return String
	 */
	private String connectionRouteDecision(HttpServletRequest request, HttpServletResponse response) {
		String result = defaultUrl;
		String targetUrl = request.getHeader("REFERER");
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if(savedRequest != null) {
			result = savedRequest.getRedirectUrl();
		} else if(refererUse && StringUtils.hasText(targetUrl)) {
			result = targetUrl;
		}
		return result;
	}

	public void setRefererUse(boolean refererUse) {
		this.refererUse = refererUse;
	}

	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}
}
