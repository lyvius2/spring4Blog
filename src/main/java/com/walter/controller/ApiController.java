package com.walter.controller;

import com.google.gson.Gson;
import com.walter.dao.ApiDao;
import com.walter.model.CodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yhwang131 on 2016-08-24.
 */
@Controller
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private ApiDao apiDao;

	@Autowired
	private Gson gson;

	@RequestMapping("/codeList")
	@ResponseBody
	public String getCodeList(@ModelAttribute CodeVO codeVO) {
		return gson.toJson(apiDao.getCodeList(codeVO));
	}
}
