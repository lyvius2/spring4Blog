package com.walter.controller;

import com.walter.model.AbilityVO;
import com.walter.model.ActVO;
import com.walter.model.ResumeVO;
import com.walter.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yhwang131 on 2017-06-20.
 */
@Controller
public class ResumeController extends BaseController {

	@Autowired
	private ResumeRepository repository;

	@RequestMapping(value = "/resume", method = RequestMethod.GET)
	public String resumeView() {
		/*
		ResumeVO resumeVO = new ResumeVO();
		resumeVO.setName("테스트");
		resumeVO.setEng_name("Test Man");

		repository.insert(resumeVO);
		*/
		List<ResumeVO> resumeVOS = repository.findAll();
		return "resume/resumeView";
	}

	@RequestMapping(value = "/resume", method = RequestMethod.POST)
	public String resumeRegist(@ModelAttribute("resumeVO") ResumeVO resumeVO, Model model, BindingResult result) {
		logger.debug("params ------->> {}", gson.toJson(resumeVO).toString());
		return "redirect:/resume";
	}

	@RequestMapping(value = "/resume/register")
	public String resumeForm(Model model) {
		ResumeVO resumeVO = new ResumeVO();

		/*AbilityVO abilityVO = new AbilityVO();
		abilityVO.setTitle("JAVA");
		abilityVO.setLevel(78);
		List<AbilityVO> abilityVOList = new ArrayList<>();
		abilityVOList.add(abilityVO);
		resumeVO.setSkill(abilityVOList);*/
		ActVO actVO = new ActVO();
		List<ActVO> actVOList = new ArrayList<>();
		actVOList.add(actVO);
		resumeVO.setExperience(actVOList);
		//resumeVO.setTech(0, "#JAVA#Node.js#SQL");
		logger.debug("response params ------->> {}", gson.toJson(resumeVO).toString());
		model.addAttribute("resumeVO", new ResumeVO());
		return "resume/resumeForm";
	}

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		simpleDateFormat.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
	}
}
