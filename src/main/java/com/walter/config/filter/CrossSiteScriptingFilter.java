package com.walter.config.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yhwang131 on 2016-10-17.
 */
public class CrossSiteScriptingFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
		if(httpServletRequest.getMethod().equals("GET") || httpServletRequest.getMethod().equals("POST")){
			filterChain.doFilter(new RequestWrapper(httpServletRequest), httpServletResponse);
		} else {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
		}
	}
}
