package com.walter.controller;

import com.walter.dao.CategoryDao;
import com.walter.model.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yhwang131 on 2016-09-13.
 */
@Controller
public class ConfigController extends BaseController {

	@Autowired
	private CategoryDao categoryDao;
	private String result = new String();

	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public String categoryConfigMap(Model model) {
		return "config/categoryConfig";
	}

	@RequestMapping(value = "/category", method = RequestMethod.POST)
	@ResponseBody
	public String setCategoryConfig(@ModelAttribute("categoryVO") CategoryVO categoryVO, Model model) throws SQLException {
		try {
			if(categoryVO.getCategory_cd() == 0){
				if(categoryVO.getParent_category_cd() == 0){
					categoryVO.setDepth(1);
					categoryVO.setCategory_cd(categoryDao.getNewCategoryCd(1));
				} else {
					categoryVO.setDepth(2);
					categoryVO.setCategory_cd(categoryDao.getNewCategoryCd(2));
				}
			}
			categoryVO.setReg_id(super.getLoginUser()!=null?super.getLoginUser().getUsername():"anonymous");
			categoryDao.setCategory(categoryVO);
			result = "success";
		} catch(Exception e) {
			logger.error(e.getMessage());
			result = "failure";
		}
		return gson.toJson(result);
	}

	@RequestMapping(value = "/category", method = RequestMethod.DELETE)
	@ResponseBody
	public String delCategory(@ModelAttribute("categoryVO") CategoryVO categoryVO, Model model) throws SQLException {
		categoryDao.delCategory(categoryVO.getCategory_cd());
		return gson.toJson(categoryVO.getParent_category_cd());
	}

	@RequestMapping(value = "/category/setActiveOption", method = RequestMethod.POST)
	@ResponseBody
	public String setActiveOption(@ModelAttribute("categoryVO") CategoryVO categoryVO, Model model) throws SQLException {
		try {
			categoryVO.setReg_id(super.getLoginUser()!=null?super.getLoginUser().getUsername():"anonymous");
			categoryDao.setActiveOption(categoryVO);
			result = "success";
		} catch(Exception e) {
			logger.error(e.getMessage());
			result = "failure";
		}
		return gson.toJson(result);
	}

	@RequestMapping("/category/setOrder")
	@ResponseBody
	public String setOrder(@ModelAttribute("categoryVO") CategoryVO categoryVO, Model model) throws SQLException {
		categoryVO.setReg_id(super.getLoginUser()!=null?super.getLoginUser().getUsername():"anonymous");
		categoryDao.setOrder(categoryVO);
		return gson.toJson("success");
	}
}
