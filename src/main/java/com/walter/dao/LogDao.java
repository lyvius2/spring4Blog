package com.walter.dao;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yhwang131 on 2017-07-14.
 */
@Component
public interface LogDao {
	int insExceptionLog(HashMap<String, Object> paramsMap);
	List<HashMap<String, Object>> getExceptionLogList(HashMap<String, Object> paramsMap);
	Integer getExceptionLogCount(HashMap<String, Object> paramsMap);
	List<String> getExceptionOptions();
}
