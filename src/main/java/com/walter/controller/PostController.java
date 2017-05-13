package com.walter.controller;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.*;
import com.google.api.services.drive.model.File;
import com.walter.config.CustomStringUtils;
import com.walter.model.*;
import com.walter.service.GoogleDriveService;
import com.walter.service.PostService;
import com.walter.util.MediaImageMetadata;
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
	public String registerPost(@ModelAttribute("postVO") @Valid PostVO postVO, Errors errors) throws IOException {
		if (errors.hasErrors()) {
			return "post/postForm";
		}
		if (postVO.getDelegate_img_file() != null) {
			File uploadFile = googleDriveImageService.createFile(postVO.getDelegate_img_file());
			postVO.setDelegate_img(uploadFile.getId());
		}
		postVO.setReg_id(super.getLoginUser()!=null?super.getLoginUser().getUsername():"anonymous");
		service.setPost(postVO);
		return "redirect:" + postVO.getPost_cd();
	}

	@RequestMapping(value = "/{post_cd}")
	public String postView(@PathVariable int post_cd, Model model, HttpServletRequest request)
			throws IOException, ImageProcessingException {
		int currPageNo
				= CustomStringUtils.setDefaultNumber(request.getParameter("currPageNo"), 1);
		int category_cd
				= CustomStringUtils.setDefaultNumber(request.getParameter("category_cd"), 0);
		PostVO postVO = service.getPost(post_cd);
		model.addAttribute("currPageNo", currPageNo);
		model.addAttribute("category_cd", category_cd);
		model.addAttribute("post", postVO);
		if (postVO.getDelegate_img() != null && !postVO.getDelegate_img().isEmpty()) {
			HashMap<String, Object> hashMap = googleDriveImageService.openFile(postVO.getDelegate_img());
			model.addAttribute("image_spec", MediaImageMetadata.getImageSpecString((InputStream)hashMap.get("data")));
		}
		//return "post/postView";
		return "post/tempPostView";
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

	@RequestMapping(value = "/comment", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getCommentList(HttpServletRequest request) {
		int postCd = CustomStringUtils.setDefaultNumber(request.getParameter("postCd"), -1);
		return gson.toJson(service.getComments(postCd));
	}

	@RequestMapping(value = "/comment", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String registerComment(@ModelAttribute("commentVO")CommentVO commentVO, HttpServletRequest request) {
		HashMap<String, Object> hashMap = new HashMap<>();
		if(CustomStringUtils.isNotEmpty(commentVO.getComment())) {
			commentVO.setUserData(super.getLoginUser());
			commentVO.setIp(request.getRemoteAddr());
			service.setComment(commentVO);
			hashMap.put("status", true);
		} else {
			hashMap.put("status", false);
		}
		return gson.toJson(hashMap);
	}

	@RequestMapping(value = "/reply", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String registerReply(@ModelAttribute("replyVo")ReplyVO replyVO, HttpServletRequest request) {
		HashMap<String, Object> hashMap = new HashMap<>();
		if(CustomStringUtils.isNotEmpty(replyVO.getComment())) {
			replyVO.setUserData(super.getLoginUser());
			replyVO.setIp(request.getRemoteAddr());
			service.setReply(request.getParameter("_id"), replyVO);
			hashMap.put("status", true);
		} else {
			hashMap.put("status", false);
		}
		return gson.toJson(hashMap);
	}
}
