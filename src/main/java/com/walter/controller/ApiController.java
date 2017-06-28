package com.walter.controller;

import com.walter.dao.CodeDao;
import com.walter.model.CodeVO;
import com.walter.service.ConfigService;
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
public class ApiController extends BaseController {

	@Autowired
	private ConfigService configService;

	@RequestMapping(value = "/codeList", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getCodeList(@ModelAttribute CodeVO codeVO) {
		return gson.toJson(configService.getCodeList(codeVO));
	}
}
