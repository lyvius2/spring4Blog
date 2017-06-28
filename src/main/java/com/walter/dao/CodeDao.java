package com.walter.dao;

import com.walter.model.CategoryVO;
import com.walter.model.CodeVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by yhwang131 on 2016-08-24.
 */
@Component
public interface CodeDao {
	List<CodeVO> getCodeList(CodeVO codeVO);
}
