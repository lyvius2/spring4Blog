package com.walter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yhwang131 on 2016-09-13.
 */
@Controller
public class ConfigController {

	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public String categoryConfigMap(Model model) {
		return "config/categoryConfig";
	}
}
