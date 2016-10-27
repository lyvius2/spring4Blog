package com.walter.service;

import com.walter.dao.ApiDao;
import com.walter.dao.PostDao;
import com.walter.model.CategoryVO;
import com.walter.model.CodeVO;
import com.walter.model.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yhwang131 on 2016-10-27.
 */
@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDao dao;

	@Autowired
	private ApiDao apiDao;

	@Override
	public Model setInputForm(Model model) {
		List<CategoryVO> categoryList = new ArrayList<>();
		apiDao.getCategoryList(new CategoryVO(1,0)).stream()
				.forEach(category -> {
					categoryList.add(category);
					categoryList.addAll(apiDao.getCategoryList(new CategoryVO(2,category.getCategory_cd())));
				});

		model.addAttribute("categoryList", categoryList);
		model.addAttribute("countryList", apiDao.getCodeList(new CodeVO("NAT")));
		return model;
	}

	@Override
	public int setPost(PostVO postVO) {
		return dao.setPost(postVO);
	}

	@Override
	public PostVO getPost(int post_cd) {
		PostVO postVO = dao.getPost(post_cd);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		postVO.setDf_reg_dt(df.format(postVO.getReg_dt()));
		if(postVO.getMod_dt() != null) {
			postVO.setDf_mod_dt(df.format(postVO.getMod_dt()));
		}
		return postVO;
	}
}
