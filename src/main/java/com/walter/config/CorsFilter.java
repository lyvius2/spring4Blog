package com.walter.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yhwang131 on 2016-10-13.
 */
public class CorsFilter extends OncePerRequestFilter {

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
		httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
		httpServletResponse.setHeader("X-Frame-Options", "ALLOW-FROM http://localhost:8080, DENY");
		if(httpServletRequest.getHeader("Access-Control-Request-Method")!=null && "OPTIONS".equals(httpServletRequest.getMethod())) {
			httpServletResponse.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE");
			httpServletResponse.addHeader("Access-Control-Allow-Headers", "X-Requested-With,Origin,Content-Type,Accept");
		}
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}
