package com.walter.controller;

import com.walter.dao.ApiDao;
import com.walter.dao.PostDao;
import com.walter.model.CategoryVO;
import com.walter.model.CodeVO;
import com.walter.model.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

	@Autowired
	private PostDao postDao;

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
			model.addAttribute("countryList", apiDao.getCodeList(new CodeVO("NAT")));
		} catch(Exception e) {
			logger.error(e.getMessage());
		} finally {
			model.addAttribute("postVO", new PostVO(true, true));
		}
		return "post/postForm";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPost(@ModelAttribute("postVO") @Valid PostVO postVO, Errors errors) {
		if(errors.hasErrors()) {
			return "post/postForm";
		}
		postVO.setReg_id(super.getLoginUser()!=null?super.getLoginUser().getUsername():"anonymous");
		postDao.setPost(postVO);
		return "redirect:" + postVO.getPost_cd();
	}

	@RequestMapping(value = "/{post_cd}")
	public String postView(@PathVariable int post_cd, Model model) {
		PostVO postVO = postDao.getPost(post_cd);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		model.addAttribute("post", postVO);
		model.addAttribute("regDt", df.format(postVO.getReg_dt()));
		return "post/postView";
	}
}
