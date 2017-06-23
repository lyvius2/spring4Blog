package com.walter.controller;

import com.walter.model.AbilityVO;
import com.walter.model.ActVO;
import com.walter.model.ResumeVO;
import com.walter.repository.ResumeRepository;
import com.walter.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(value = "/resume")
public class ResumeController extends BaseController {

	@Autowired
	private ResumeRepository repository;

	@Autowired
	private ResumeService service;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String resumeView() {
		/*
		ResumeVO resumeVO = new ResumeVO();
		resumeVO.setName("테스트");
		resumeVO.setEng_name("Test Man");

		repository.insert(resumeVO);
		*/
		List<ResumeVO> resumeVOS = repository.findAll();
		logger.debug("Datas : {}", resumeVOS.get(0).toString());
		logger.debug("findById1 : {}", repository.findBy_id(resumeVOS.get(0).get_id()));
		return "resume/resumeView";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String resumeRegist(@ModelAttribute("resumeVO") ResumeVO resumeVO, Model model, BindingResult result) {
		logger.debug("params ------->> {}", gson.toJson(resumeVO).toString());
		return "redirect:/resume";
	}

	@RequestMapping(value = "/register")
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

	@RequestMapping(value = "/api")
	public ResponseEntity resumeViewApi(@RequestParam(value = "_id", required = false) String _id) {
		return new ResponseEntity(service.getDefaultResume(_id), HttpStatus.OK);
	}
}
