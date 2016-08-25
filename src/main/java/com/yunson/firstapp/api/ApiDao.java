package com.yunson.firstapp.api;

import java.util.List;

/**
 * Created by yhwang131 on 2016-08-24.
 */
public interface ApiDao {
	List<CodeVO> getCodeList(CodeVO codeVO);
}
