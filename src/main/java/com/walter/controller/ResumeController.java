package com.walter.controller;

import com.walter.model.ResumeVO;
import com.walter.service.ResumeService;
import com.walter.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by yhwang131 on 2017-06-20.
 */
@Controller
@RequestMapping(value = "/resume")
public class ResumeController extends BaseController {

	@Autowired
	private ResumeService service;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String resumeView(Model model) {
		ResumeVO resumeVO = service.getDefaultResume(null);
		model.addAttribute("resume", resumeVO);
		return "resume/resumeView";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String resumeRegist(@ModelAttribute("resumeVO") ResumeVO resumeVO,
	                           @RequestParam("profile")MultipartFile file,
	                           BindingResult result) throws IOException {
		service.registerResume(resumeVO, file);
		return "redirect:/resume";
	}

	@RequestMapping(value = "/register")
	public String resumeForm() {
		return "resume/resumeForm";
	}

	@RequestMapping(value = "/api")
	public ResponseEntity resumeViewApi(@RequestParam(value = "_id", required = false) String _id) {
		return new ResponseEntity(service.getDefaultResume(_id), HttpStatus.OK);
	}
}
