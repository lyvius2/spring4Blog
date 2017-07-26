package com.walter.controller;

import com.walter.model.ResumeVO;
import com.walter.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

/**
 * Resume Controller (Profile)
 * Created by yhwang131 on 2017-06-20.
 */
@Controller
@RequestMapping(value = "/resume")
public class ResumeController extends BaseController {

	@Autowired
	private ResumeService service;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String resumeView(@RequestParam(value = "_id", defaultValue = "", required = false) String _id, Model model) {
		ResumeVO resumeVO = service.getResume(_id);
		model.addAttribute("resume", resumeVO);
		return "resume/resumeView";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String resumeRegist(@ModelAttribute("resumeVO") ResumeVO resumeVO,
	                           @RequestParam("profile")MultipartFile file,
	                           BindingResult result) throws IOException {
		service.setResume(resumeVO, file);
		return "redirect:/resume";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity removeResume(@RequestParam(value = "_id") String _id) {
		HashMap<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", service.removeResume(_id));
		return super.createResEntity(resultMap);
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/register")
	public String resumeForm() {
		return "resume/resumeForm";
	}
}
