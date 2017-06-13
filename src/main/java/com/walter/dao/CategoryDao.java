package com.walter.dao;

import com.walter.model.CategoryVO;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yhwang131 on 2016-10-04.
 */
@Component
public interface CategoryDao {
	List<CategoryVO> getCategoryList();
	CategoryVO getCategoryItem(int category_cd);
	int insCategoryItem(CategoryVO categoryVO);
	int modCategoryItem(HashMap paramMap);

	Integer getNewCategoryItemCd();

	int setCategory(CategoryVO categoryVO);
	int setActiveOption(CategoryVO categoryVO);
	int delCategory(int categoryCd);
	int setOrder(CategoryVO categoryVO);
}
