package com.walter.dao;

import com.walter.model.CodeVO;

import java.util.List;

/**
 * Created by yhwang131 on 2016-08-24.
 */
public interface ApiDao {
	List<CodeVO> getCodeList(CodeVO codeVO);
}
