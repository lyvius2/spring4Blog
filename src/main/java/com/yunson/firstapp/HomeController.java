package com.yunson.firstapp;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import com.yunson.firstapp.member.MemberVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.access.method.P;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	public static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	protected final MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	
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
		MemberVO memberVO = this.getLoginUser();
		model.addAttribute("message",
				messages.getMessage("JdbcDaoImpl.noAuthority", new Object[]{memberVO!=null?memberVO.getUsername():""}, "User {0} has no GrantedAuthority"));
		return "403";
	}

	private MemberVO getLoginUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		if(principal != null && principal instanceof MemberVO) {
			return (MemberVO)principal;
		} else {
			return null;
		}
	}
}
