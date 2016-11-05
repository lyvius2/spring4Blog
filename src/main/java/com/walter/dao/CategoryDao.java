package com.walter.dao;

import com.walter.model.CategoryVO;

/**
 * Created by yhwang131 on 2016-10-04.
 */
public interface CategoryDao {
	Integer getNewCategoryCd(int depth);
	int setCategory(CategoryVO categoryVO);
	int setActiveOption(CategoryVO categoryVO);
	int delCategory(int categoryCd);
	int setOrder(CategoryVO categoryVO);
}
