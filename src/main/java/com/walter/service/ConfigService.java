package com.walter.service;

import com.walter.model.CategoryVO;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yhwang131 on 2017-05-23.
 */
public interface ConfigService {
	List<CategoryVO> getCategoryList();
	CategoryVO getCategoryItem(int category_cd);
	HashMap modCategoryItem(CategoryVO categoryVO, String targetAttribute);
}
