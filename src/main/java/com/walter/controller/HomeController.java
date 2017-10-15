package com.walter.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import com.walter.model.MemberVO;
import com.walter.model.PostSearchVO;
import com.walter.model.PostVO;
import com.walter.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends BaseController {

	@Autowired
	private PostService postService;

	/**
	 * 기본 Page (Blog Post List)
	 * @param category_cd
	 * @param locale
	 * @return
	 * @throws IllegalStateException
	 */
	@RequestMapping(value = "")
	public String main(@RequestParam(name = "category_cd", required = false)String category_cd, Locale locale)
			throws IllegalStateException {
		logger.info("Welcome Walter's Home! The client locale is {}.", locale);
		if (category_cd != null && !category_cd.equals("")) {
			PostSearchVO postSearchVO = new PostSearchVO();
			postSearchVO.setUse_yn(true);
			postSearchVO.setCategory_cd(Integer.parseInt(category_cd));
			postSearchVO.setRowsPerPage(1);
			List<PostVO> postList = postService.getPostList(postSearchVO);
			if (postList.size() > 0) return "redirect:post/" + postList.get(0).getPost_cd();
		}
		return "post/postList";
	}

	/**
	 * 미인증 상태 시 Redirect
	 * @param redirectAttr
	 * @return
	 */
	@RequestMapping("/401")
	public String noAuthority(RedirectAttributes redirectAttr) {
		redirectAttr.addFlashAttribute("requestSignIn", true);
		return "redirect:/";
	}

	/**
	 * 권한없음 Page
	 * @param model
	 * @return
	 * @throws NullPointerException
	 */
	@RequestMapping("/403")
	public String noPermission(Model model) throws NullPointerException {
		MemberVO memberVO = super.getLoginUser();
		model.addAttribute("message",
				messages.getMessage("JdbcDaoImpl.noAuthority", new Object[]{memberVO!=null?memberVO.getUsername():""}, "User {0} has no GrantedAuthority"));
		return "status/403";
	}

	@RequestMapping("/healthCheck")
	public ResponseEntity healthChk() {
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("status", "OK");
		hashMap.put("time", new Date());
		return super.createResEntity(hashMap);
	}
}
