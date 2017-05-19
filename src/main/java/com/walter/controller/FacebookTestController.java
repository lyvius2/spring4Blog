package com.walter.controller;

import com.walter.config.SignInUserDetailsService;
import com.walter.model.MemberVO;
import com.walter.model.RoleVO;
import org.jboss.logging.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by yhwang131 on 2017-05-17.
 */
@Controller
@RequestMapping("/connect")
public class FacebookTestController extends ConnectController {
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name="signInUserDetailsService")
	private SignInUserDetailsService signInUserDetailsService;

	@Resource(name="inMemoryConnectionRepository")
	private ConnectionRepository connectionRepository;

	@Inject
	public FacebookTestController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
		super(connectionFactoryLocator, connectionRepository);
	}

	@RequestMapping(value="/{providerId}", method= RequestMethod.GET, params="code")
	public RedirectView oauth2Callback(@PathVariable String providerId, NativeWebRequest request) {
		logger.debug("code : " + request.getParameter("code"));
		logger.debug("state : " + request.getParameter("state"));
		HttpServletRequest httpServletRequest = (HttpServletRequest)request.getNativeRequest();
		httpServletRequest.getSession().setAttribute("code", request.getParameter("code"));
		RedirectView redirectView = super.oauth2Callback(providerId, request);

		User userProfile = new User(null, null, null, null, null, null);

		try {
			Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
			Facebook facebook = connection.getApi();
			String [] fields = { "id", "age_range", "email", "first_name", "gender",
					"last_name", "link", "locale", "name", "third_party_id", "verified" };
			//connection.createData().getImageUrl();    //Profile Image
			userProfile = facebook.fetchObject("me", User.class, fields);
		} catch(Exception e) {
			logger.error(e.toString());
		}

		signInUserDetailsService.onAuthenticationBinding(new MemberVO(), userProfile, httpServletRequest.getSession());
		String targetUrl = httpServletRequest.getHeader("REFERER");
		redirectView.setUrl(targetUrl);
		return redirectView;
	}
}
