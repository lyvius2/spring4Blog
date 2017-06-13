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
	public HashMap insCategoryItem(CategoryVO categoryVO) {
		HashMap paramsMap = new HashMap();
		try {
			int result = categoryDao.insCategoryItem(categoryVO);
			paramsMap.put("success", result == 1 ? true:false);
		} catch (Exception e) {
			logger.error(e.toString());
			paramsMap.put("success", false);
			paramsMap.put("message", e.getMessage());
		}
		return paramsMap;
	}

	@Override
	public HashMap modCategoryItem(CategoryVO categoryVO, String targetAttribute) {
		HashMap paramsMap = new HashMap();
		try {
			paramsMap.put("mod_id", categoryVO.getMod_id());
			paramsMap.put("category_cd", categoryVO.getCategory_cd());
			switch (targetAttribute) {
				case "Usage" :
					paramsMap.put("use_yn", categoryVO.isUse_yn());
					break;
				case "Order" :
					paramsMap.put("order_no", categoryVO.getOrder_no());
					break;
				case "Name" :
					paramsMap.put("category_name", categoryVO.getCategory_name());
					break;
			}
			int result = categoryDao.modCategoryItem(paramsMap);
			paramsMap.put("success", result == 1 ? true:false);
		} catch (Exception e) {
			logger.error(e.toString());
			paramsMap.put("success", false);
			paramsMap.put("message", e.getMessage());
		}
		return paramsMap;
	}
}
