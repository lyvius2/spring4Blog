package com.walter.service;

import com.walter.config.code.Message;
import com.walter.model.CategoryVO;
import com.walter.model.CodeVO;
import com.walter.model.MemberVO;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yhwang131 on 2017-05-23.
 */
public interface ConfigService {
	Message insAdmin(MemberVO memberVO, boolean isCreate);
	List<MemberVO> getMemberList();

	List<CategoryVO> getCategoryList();
	CategoryVO getCategoryItemByCd(int category_cd);
	HashMap insCategoryItem(CategoryVO categoryVO);
	HashMap modCategoryItem(CategoryVO categoryVO, String targetAttribute);
	HashMap delCategoryItem(int category_cd);

	List<CodeVO> getCodeList(CodeVO codeVO);

	HashMap<String, Object> getExceptionList(int currPageNo, String exception);
	List<String> getExceptionOptions();
}
