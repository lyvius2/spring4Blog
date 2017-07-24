package com.walter.controller;

import java.util.Locale;

import com.walter.model.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends BaseController {

	/**
	 * 기본 Page
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale) {
		logger.info("Welcome home! The client locale is {}.", locale);
		return "index";
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
}
