package com.walter.controller;

import com.walter.util.CustomStringUtils;
import com.walter.config.authentication.SignInUserDetailsService;
import com.walter.model.MemberVO;
import com.walter.service.SocialService;
import com.walter.config.code.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UrlPathHelper;
import org.springframework.web.util.WebUtils;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Social SignIn Controller
 * Created by yhwang131 on 2017-05-17.
 */
@Controller
@RequestMapping("/connect")
public class SocialController extends ConnectController {
	private final UrlPathHelper urlPathHelper = new UrlPathHelper();

	@Autowired
	private SocialService service;

	@Autowired
	private HttpServletRequest httpServletRequest;

	@Autowired
	private SignInUserDetailsService signInUserDetailsService;

	@Inject
	public SocialController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
		super(connectionFactoryLocator, connectionRepository);
	}

	@RequestMapping(value="/{providerId}", method=RequestMethod.POST)
	public RedirectView connect(@PathVariable String providerId, NativeWebRequest request) {
		httpServletRequest.getSession().setAttribute("referer", httpServletRequest.getHeader("REFERER"));
		return super.connect(providerId, request);
	}

	@RequestMapping(value="/{providerId}", method= RequestMethod.GET, params="code")
	public RedirectView oauth2Callback(@PathVariable String providerId, NativeWebRequest request) {
		MemberVO memberVO = service.bindingSocialUserInfo(providerId, request);
		HttpServletRequest httpServletRequest = request.getNativeRequest(HttpServletRequest.class);
		String path = "/connect/" + providerId + this.getPathExtension(httpServletRequest);
		if (this.prependServletPath(httpServletRequest)) path = httpServletRequest.getServletPath() + path;
		if (memberVO != null) signInUserDetailsService.onAuthenticationWithSocial(memberVO);
		else {
			httpServletRequest.getSession().setAttribute("msg",
					CustomStringUtils.executeAlertMessage(Message.ERROR_SOCIAL_API.getText()));
		}
		RedirectView redirectView = new RedirectView(path, true);
		redirectView.setUrl(httpServletRequest.getSession().getAttribute("referer").toString());
		return redirectView;
	}

	private boolean prependServletPath(HttpServletRequest request) {
		return !this.urlPathHelper.getPathWithinServletMapping(request).equals("");
	}

	private String getPathExtension(HttpServletRequest request) {
		String fileName = WebUtils.extractFullFilenameFromUrlPath(request.getRequestURI());
		String extension = StringUtils.getFilenameExtension(fileName);
		return extension != null ? "." + extension : "";
	}
}
