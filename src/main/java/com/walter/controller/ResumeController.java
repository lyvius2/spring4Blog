package com.walter.controller;

import com.walter.model.ResumeVO;
import com.walter.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by yhwang131 on 2017-06-20.
 */
@Controller
public class ResumeController extends BaseController {

	@Autowired
	private ResumeRepository repository;

	@RequestMapping(value = "/resume")
	@ResponseBody
	public String resumeView() {
		/*
		ResumeVO resumeVO = new ResumeVO();
		resumeVO.setName("테스트");
		resumeVO.setEng_name("Test Man");

		repository.insert(resumeVO);
		*/
		List<ResumeVO> resumeVOS = repository.findAll();
		return gson.toJson(resumeVOS.get(0));
	}
}
