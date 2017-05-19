package com.walter.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by yhwang131 on 2016-08-29.
 */
public class SignInFailureHandler implements AuthenticationFailureHandler {

	public static final Logger logger = LoggerFactory.getLogger(SignInFailureHandler.class);
	private String id;
	private String pw;
	private String defaultFailureUrl;
	private String errorMsg;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		/*
		request.setAttribute(id, request.getParameter(id));
		request.setAttribute(pw, request.getParameter(pw));
		request.setAttribute(errorMsg, exception.getMessage());
		*/
		//response.sendRedirect(request.getContextPath() + defaultFailureUrl);
		//request.getRequestDispatcher(defaultFailureUrl).forward(request, response);

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("authenticated", false);
		jsonObject.addProperty("errorMsg", exception.getMessage());

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(jsonObject.toString());
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public void setDefaultFailureUrl(String defaultFailureUrl) {
		this.defaultFailureUrl = defaultFailureUrl;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
