package com.walter.dao;

import com.walter.model.CategoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yhwang131 on 2016-10-04.
 */
@Component
@Mapper
public interface CategoryDao {
	List<CategoryVO> getCategoryList();
	CategoryVO getCategoryItemByCd(int category_cd);
	int getCategoryCountByName(String category_name);
	int insCategoryItem(CategoryVO categoryVO);
	int modCategoryItem(HashMap paramMap);
	int delCategoryItem(int categoryCd);
}
