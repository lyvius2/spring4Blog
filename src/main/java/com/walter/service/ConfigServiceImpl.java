package com.walter.service;

import com.walter.dao.CategoryDao;
import com.walter.model.CategoryVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yhwang131 on 2017-05-23.
 */
@Service
public class ConfigServiceImpl implements ConfigService {
	final Logger logger = LoggerFactory.getLogger(this.getClass());

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

	@Override
	public HashMap modCategoryItem(CategoryVO categoryVO) {
		HashMap status = new HashMap();
		try {
			int result = categoryDao.modCategoryItem(categoryVO);
			status.put("success", result == 1 ? true:false);
		} catch (Exception e) {
			logger.error(e.toString());
			status.put("success", false);
			status.put("message", e.getMessage());
		}
		return status;
	}
}
