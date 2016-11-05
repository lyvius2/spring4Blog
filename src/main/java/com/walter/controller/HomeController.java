package com.walter.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import com.walter.model.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends BaseController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession httpSession) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		return "index";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/exclude/403")
	public String noAuthority(Model model) throws NullPointerException {
		MemberVO memberVO = super.getLoginUser();
		model.addAttribute("message",
				messages.getMessage("JdbcDaoImpl.noAuthority", new Object[]{memberVO!=null?memberVO.getUsername():""}, "User {0} has no GrantedAuthority"));
		return "403";
	}
}
