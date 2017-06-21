package com.walter.controller;

import com.walter.model.ResumeVO;
import com.walter.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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
	public String resumeRegist(@ModelAttribute("resumeVO") ResumeVO resumeVO, Model model) {
		logger.debug("params ------->> {}", gson.toJson(resumeVO).toString());
		return "redirect:/resume";
	}

	@RequestMapping(value = "/resume/register")
	public String resumeForm(Model model) {
		ResumeVO resumeVO = new ResumeVO();
		resumeVO.setTech(0, "#JAVA#Node.js#SQL");
		logger.debug("response params ------->> {}", gson.toJson(resumeVO).toString());
		model.addAttribute("resumeVO", resumeVO);
		return "resume/resumeForm";
	}
}
