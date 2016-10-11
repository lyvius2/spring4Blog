package com.walter.controller;

import com.walter.dao.ApiDao;
import com.walter.model.CategoryVO;
import com.walter.model.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yhwang131 on 2016-10-11.
 */
@Controller
@RequestMapping("/post")
public class PostController extends BaseController {

	@Autowired
	private ApiDao apiDao;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerPostForm(Model model) {
		try {
			List<CategoryVO> categoryList = new ArrayList<>();
			apiDao.getCategoryList(new CategoryVO(1,0)).stream()
					.forEach(category -> {
						categoryList.add(category);
						categoryList.addAll(apiDao.getCategoryList(new CategoryVO(2,category.getCategory_cd())));
					});
			model.addAttribute("categoryList", categoryList);
		} catch(Exception e) {
			logger.error(e.getMessage());
		} finally {
			model.addAttribute("postVO", new PostVO(true, true));
		}
		return "post/postForm";
	}
}
