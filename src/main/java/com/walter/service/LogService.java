package com.walter.service;

import com.walter.model.ExceptionVO;

import java.util.HashMap;
import java.util.List;

/**
 * Log Service (Exception, Access)
 * Created by yhwang131 on 2017-07-18.
 */
public interface LogService {
	ExceptionVO setException(ExceptionVO exceptionVO);
	HashMap<String, Object> getExceptionList(String exception, int currPageNo);
	List<String> getExceptionOptions();
}
