package com.walter.controller;

import com.google.api.services.drive.model.File;
import com.walter.dao.ApiDao;
import com.walter.dao.PostDao;
import com.walter.model.CategoryVO;
import com.walter.model.CodeVO;
import com.walter.model.PostVO;
import com.walter.service.GoogleDriveService;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yhwang131 on 2016-10-11.
 */
@Controller
@RequestMapping("/post")
public class PostController extends BaseController {

	@Autowired
	private ApiDao apiDao;

	@Autowired
	private PostDao postDao;

	@Resource(name = "googleDriveServiceImageImpl")
	private GoogleDriveService googleDriveImageService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerPostForm(Model model) {
		try {
			List<CategoryVO> categoryList = new ArrayList<>();
			apiDao.getCategoryList(new CategoryVO(1,0)).stream()
					.forEach(category -> {
						categoryList.add(category);
						categoryList.addAll(apiDao.getCategoryList(new CategoryVO(2,category.getCategory_cd())));
					});
			model.addAttribute("categoryList", categoryList);
			model.addAttribute("countryList", apiDao.getCodeList(new CodeVO("NAT")));
		} catch(Exception e) {
			logger.error(e.getMessage());
		} finally {
			model.addAttribute("postVO", new PostVO(true, true));
		}
		return "post/postForm";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPost(@ModelAttribute("postVO") @Valid PostVO postVO, Errors errors) {
		if(errors.hasErrors()) {
			return "post/postForm";
		}
		postVO.setReg_id(super.getLoginUser()!=null?super.getLoginUser().getUsername():"anonymous");
		postDao.setPost(postVO);
		return "redirect:" + postVO.getPost_cd();
	}

	@RequestMapping(value = "/{post_cd}")
	public String postView(@PathVariable int post_cd, Model model) {
		PostVO postVO = postDao.getPost(post_cd);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		model.addAttribute("post", postVO);
		model.addAttribute("regDt", df.format(postVO.getReg_dt()));
		return "post/postView";
	}

	@RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
	public String fileUploadForm() {
		return "post/fileUpload";
	}

	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
	public String imgUpload(@RequestParam("upload")MultipartFile file, Model model, HttpServletRequest httpServletRequest) throws IOException {
		File resultFile = googleDriveImageService.createFile(file);
		model.addAttribute("CKEditorFuncNum", httpServletRequest.getParameter("CKEditorFuncNum"));
		model.addAttribute("fileURL", "\\/post\\/images\\/" + resultFile.getId());
		return "post/imgUpload";
	}

	@RequestMapping(value = "/images/{file_id}", method = RequestMethod.GET)
	public void imgView(@PathVariable String file_id, HttpServletResponse httpServletResponse) throws IOException {
		HashMap<String, Object> hashMap = googleDriveImageService.openFile(file_id);
		httpServletResponse.setContentType(hashMap.get("mimeType").toString());
		httpServletResponse.getOutputStream().write(IOUtils.toByteArray((InputStream)hashMap.get("data")));
	}
}
