package com.walter.controller;

import com.walter.config.CustomStringUtils;
import com.walter.config.code.Message;
import com.walter.model.CategoryVO;
import com.walter.model.MemberVO;
import com.walter.service.ConfigService;
import com.walter.service.LogService;
import com.walter.service.ResumeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;

/**
 * Config Controller
 * Created by yhwang131 on 2016-09-13.
 */
@Controller
@RequestMapping("/config")
public class ConfigController extends BaseController {

	@Autowired
	private ConfigService configService;

	@Autowired
	private ResumeService resumeService;

	@Autowired
	private LogService logService;

	@RequestMapping(value = "")
	public String configView(Model model) {
		model.addAttribute("categoryVO", new CategoryVO());
		model.addAttribute("memberVO", new MemberVO());
		model.addAttribute("memberList", configService.getMemberList());
		model.addAttribute("resumeList", resumeService.getResumeList());
		model.addAttribute("methodOptions", logService.getAccessLogOptions());
		model.addAttribute("exceptionOptions", logService.getExceptionOptions());
		return "config/config";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public String registerAdmin(@ModelAttribute("memberVO") @Valid MemberVO memberVO, Errors errors, Model model, HttpServletRequest request) {
		boolean isCreate = StringUtils.stripToEmpty(request.getParameter("mode")).equals("C");
		if(errors.hasErrors() && isCreate) {
			model.addAttribute("memberList", configService.getMemberList());
			return "config/config";
		}
		if(!memberVO.getPassword().equals(request.getParameter("passwordConfirm"))) {
			model.addAttribute("memberList", configService.getMemberList());
			errors.rejectValue("password", null, Message.REQUEST_RECONFIRM_PW.getText());
			return "config/config";
		}
		Message msg = configService.insAdmin(memberVO, isCreate);
		if(msg.equals(Message.SAVED)) return "redirect:../config";
		else {
			model.addAttribute("memberList", configService.getMemberList());
			errors.rejectValue("username", null, msg.getText());
			return "config/config";
		}
	}

	@RequestMapping(value = "/setAdminUsage", method = RequestMethod.POST)
	public ResponseEntity modifyAdmin(@ModelAttribute("memberVO")MemberVO memberVO) {
		configService.insAdmin(memberVO, false);
		HashMap<String, Object> resultMap = new HashMap<>();
		resultMap.put("success", true);
		return super.createResEntity(resultMap);
	}

	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public String registerCategory(@ModelAttribute("categoryVO") @Valid CategoryVO categoryVO,
	                          RedirectAttributes redirectAttributes) throws IOException {
		categoryVO.setReg_id(super.getUsername());
		HashMap resultMap = configService.insCategoryItem(categoryVO);
		if ((Boolean)resultMap.get("success") == false) {
			redirectAttributes.addFlashAttribute("msg",
					CustomStringUtils.executeAlertMessage(resultMap.get("message").toString()));
		}
		return "redirect:../config";
	}

	@RequestMapping(value = "/category", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String removeCategory(@RequestParam(value = "category_cd") int category_cd) {
		return gson.toJson(configService.delCategoryItem(category_cd));
	}

	@RequestMapping(value = "/setCategory{targetAttribute}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String modifyCategory(@PathVariable("targetAttribute") String targetAttribute,
	                          @ModelAttribute("categoryVO") @Valid CategoryVO categoryVO, Errors errors) {
		if (errors.hasErrors()) return gson.toJson("{'success': false}");
		categoryVO.setMod_id(super.getUsername());
		return gson.toJson(configService.modCategoryItem(categoryVO, targetAttribute));
	}
}
