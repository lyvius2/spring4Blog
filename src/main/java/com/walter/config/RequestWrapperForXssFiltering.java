package com.walter.config;

import com.nhncorp.lucy.security.xss.XssFilter;
import com.nhncorp.lucy.security.xss.XssPreventer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Arrays;

/**
 * Created by yhwang131 on 2016-10-17.
 */
public class RequestWrapperForXssFiltering extends HttpServletRequestWrapper {

	public RequestWrapperForXssFiltering(HttpServletRequest httpServletRequest) {
		super(httpServletRequest);
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		if(values == null) return null;
		boolean doPreventer = getPreventerFlag(name);
		return Arrays.stream(values).map(v -> doFilter(v, doPreventer)).toArray(String[]::new);
	}

	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		if(value == null) return null;
		return doFilter(value, getPreventerFlag(name));
	}

	@Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
		if(value == null) return null;
		return doFilter(value, false);
	}

	private String doFilter(String value, boolean doPreventer) {
		if(doPreventer) {
			return XssPreventer.escape(value);
		} else {
			XssFilter xssFilter = XssFilter.getInstance();
			return xssFilter.doFilter(value);
		}
	}

	private Boolean getPreventerFlag(String name) {
		if(name == null) return true;
		return !name.toLowerCase().startsWith("content")?true:false;
	}
}
