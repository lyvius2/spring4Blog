package com.walter.controller;

import com.walter.config.CustomStringUtils;
import com.walter.model.CategoryVO;
import com.walter.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by yhwang131 on 2016-09-13.
 */
@Controller
public class ConfigController extends BaseController {

	@Autowired
	private ConfigService configService;

	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public String categoryConfigMap(Model model) {
		return "config/categoryConfig";
	}

	@RequestMapping(value = "/config")
	public String configView(Model model) {
		model.addAttribute("categoryVO", new CategoryVO());
		//model.addAttribute("categories", configService.getCategoryList());
		return "config/config";
	}

	@RequestMapping(value = "/config/category", method = RequestMethod.POST)
	public String insCategory(@ModelAttribute("categoryVO") @Valid CategoryVO categoryVO,
	                          RedirectAttributes redirectAttributes) throws IOException {
		categoryVO.setReg_id(super.getUsername());
		HashMap resultMap = configService.insCategoryItem(categoryVO);
		if ((Boolean)resultMap.get("success") == false) {
			redirectAttributes.addFlashAttribute("msg",
					CustomStringUtils.executeAlertMessage(resultMap.get("message").toString()));
		}
		return "redirect:../config";
	}

	@RequestMapping(value = "/config/category", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String delCategory(@RequestParam(value = "category_cd") int category_cd) {
		return gson.toJson(configService.delCategoryItem(category_cd));
	}

	@RequestMapping(value = "/config/setCategory{targetAttribute}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String setCategory(@PathVariable("targetAttribute") String targetAttribute,
	                          @ModelAttribute("categoryVO") @Valid CategoryVO categoryVO, Errors errors) {
		if (errors.hasErrors()) return gson.toJson("{'success': false}");
		categoryVO.setMod_id(super.getUsername());
		return gson.toJson(configService.modCategoryItem(categoryVO, targetAttribute));
	}
}
