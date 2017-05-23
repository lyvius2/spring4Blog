package com.walter.dao;

import com.walter.model.CategoryVO;

import java.util.List;

/**
 * Created by yhwang131 on 2016-10-04.
 */
public interface CategoryDao {
	List<CategoryVO> getCategoryList();
	CategoryVO getCategoryItem(int category_cd);

	Integer getNewCategoryCd(int depth);
	int setCategory(CategoryVO categoryVO);
	int setActiveOption(CategoryVO categoryVO);
	int delCategory(int categoryCd);
	int setOrder(CategoryVO categoryVO);
}
