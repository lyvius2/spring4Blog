package com.walter.controller;

import com.google.api.services.drive.model.File;
import com.walter.config.CustomStringUtils;
import com.walter.model.CommentVO;
import com.walter.model.MemberVO;
import com.walter.model.PostSearchVO;
import com.walter.model.PostVO;
import com.walter.service.GoogleDriveService;
import com.walter.service.PostService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by yhwang131 on 2016-10-11.
 */
@Controller
@RequestMapping("/post")
public class PostController extends BaseController {

	@Autowired
	private PostService service;

	@Resource(name = "googleDriveServiceImage")
	private GoogleDriveService googleDriveImageService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerPostForm(Model model) {
		model = service.setInputForm(model);
		model.addAttribute("postVO", new PostVO(true, true));
		return "post/postForm";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPost(@ModelAttribute("postVO") @Valid PostVO postVO, Errors errors) {
		if(errors.hasErrors()) {
			return "post/postForm";
		}
		postVO.setReg_id(super.getLoginUser()!=null?super.getLoginUser().getUsername():"anonymous");
		service.setPost(postVO);
		return "redirect:" + postVO.getPost_cd();
	}

	@RequestMapping(value = "/{post_cd}")
	public String postView(@PathVariable int post_cd, Model model, HttpServletRequest request) {
		int currPageNo
				= CustomStringUtils.setDefaultNumber(request.getParameter("currPageNo"), 1);
		int category_cd
				= CustomStringUtils.setDefaultNumber(request.getParameter("category_cd"), 0);
		model.addAttribute("currPageNo", currPageNo);
		model.addAttribute("category_cd", category_cd);
		model.addAttribute("post", service.getPost(post_cd));
		return "post/postView";
	}

	@RequestMapping(value = "/list", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String postList(@ModelAttribute("postSearchVO")PostSearchVO postSearchVO) {
		return gson.toJson(service.getPostList(postSearchVO));
	}

	@RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
	public String fileUploadForm() {
		return "post/fileUpload";
	}

	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
	public String imgUpload(@RequestParam("upload")MultipartFile file, Model model, HttpServletRequest request) throws IOException {
		File resultFile = googleDriveImageService.createFile(file);
		model.addAttribute("CKEditorFuncNum",  request.getParameter("CKEditorFuncNum"));
		model.addAttribute("fileURL", "\\/post\\/images\\/" + resultFile.getId());
		return "post/imgUpload";
	}

	@RequestMapping(value = "/dragAndDropUpload", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String dragAndDropUpload(@RequestParam("upload")MultipartFile file) throws IOException {
		HashMap<String, Object> resultMap = new HashMap<>();
		try {
			File resultFile = googleDriveImageService.createFile(file);
			resultMap.put("uploaded", 1);
			resultMap.put("fileName", resultFile.getName());
			resultMap.put("url", "/post/images/" + resultFile.getId());
		} catch(IOException ioe) {
			ioe.printStackTrace();
			resultMap.put("uploaded", 0);
			resultMap.put("error", new HashMap<String, Object>().put("message", ioe.getMessage()));
		}
		return gson.toJson(resultMap);
	}

	@RequestMapping(value = "/images/{file_id}", method = RequestMethod.GET)
	public void imgView(@PathVariable String file_id, HttpServletResponse response) throws IOException {
		HashMap<String, Object> hashMap = googleDriveImageService.openFile(file_id);
		response.setContentType(hashMap.get("mimeType").toString());
		response.getOutputStream().write(IOUtils.toByteArray((InputStream)hashMap.get("data")));
	}

	@RequestMapping(value = "/comment/{post_cd}", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String registerComment(@PathVariable int post_cd, HttpServletRequest request) {
		MemberVO memberVO = super.getLoginUser();
		CommentVO commentVO = new CommentVO(
				post_cd,
				"yhwang131",
				"테스트맨",
				request.getRemoteAddr(),
				new Date(),
				request.getParameter("comment"));
		service.setComment(commentVO);
		return gson.toJson(commentVO);
	}

	@RequestMapping(value = "/comment/{_id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String registerReply(@PathVariable String _id, @ModelAttribute("commentVO")CommentVO comment) {
		MemberVO memberVO = super.getLoginUser();
		CommentVO commentVO = new CommentVO(
				"yhwang131",
				"답글맨",
				"110.45.164.70",
				new Date(),
				comment.getComment());
		return gson.toJson(service.setReply(_id, commentVO));
	}
}
