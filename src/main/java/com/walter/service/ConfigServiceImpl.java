package com.walter.service;

import com.walter.dao.CategoryDao;
import com.walter.model.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yhwang131 on 2017-05-23.
 */
@Service
public class ConfigServiceImpl implements ConfigService {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	public List<CategoryVO> getCategoryList() {
		return categoryDao.getCategoryList();
	}

	@Override
	public CategoryVO getCategoryItem(int category_cd) {
		return categoryDao.getCategoryItem(category_cd);
	}
}
