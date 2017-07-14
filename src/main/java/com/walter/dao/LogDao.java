package com.walter.dao;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by yhwang131 on 2017-07-14.
 */
@Component
public interface LogDao {
	int insExceptionLog(HashMap<String, Object> paramsMap);
}
