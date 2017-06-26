package com.walter.controller;

import com.walter.config.authentication.SignInUserDetailsService;
import com.walter.model.MemberVO;
import com.walter.service.SocialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.api.plus.PlusOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by yhwang131 on 2017-05-17.
 */
@Controller
@RequestMapping("/connect")
public class FacebookTestController extends ConnectController {
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String TARGET_URL = new String();

	@Resource(name="inMemoryConnectionRepository")
	private ConnectionRepository connectionRepository;

	@Autowired
	private SocialService service;

	@Autowired
	private SignInUserDetailsService signInUserDetailsService;

	@Inject
	public FacebookTestController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
		super(connectionFactoryLocator, connectionRepository);
	}

	@RequestMapping(value="/{providerId}", method=RequestMethod.POST)
	public RedirectView connect(@PathVariable String providerId, NativeWebRequest request) {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request.getNativeRequest();
		TARGET_URL = httpServletRequest.getHeader("REFERER");
		return super.connect(providerId, request);
	}

	@RequestMapping(value="/{providerId}", method= RequestMethod.GET, params="code")
	public RedirectView oauth2Callback(@PathVariable String providerId, NativeWebRequest request) {
		logger.debug("code : " + request.getParameter("code"));
		logger.debug("state : " + request.getParameter("state"));
		logger.debug("provider ID : {}", providerId);
		HttpServletRequest httpServletRequest = (HttpServletRequest)request.getNativeRequest();
		httpServletRequest.getSession().setAttribute("code", request.getParameter("code"));
		RedirectView redirectView = super.oauth2Callback(providerId, request);

		MemberVO memberVO = service.bindingSocialUserInfo(providerId);
		signInUserDetailsService.onAuthenticationWithSocial(memberVO);
		redirectView.setUrl(TARGET_URL);


/*


		if (providerId.equals("facebook")) {
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

			signInUserDetailsService.onAuthenticationBinding(new MemberVO(), userProfile);
			redirectView.setUrl(TARGET_URL);
		} else if (providerId.equals("google")) {
			try {
				/*
				Connection<Google> connection = connectionRepository.findPrimaryConnection(Google.class);
				Google google = connection.getApi();
				PlusOperations plusOperations = google.plusOperations();
				Person person = plusOperations.getGoogleProfile();
				logger.debug("=======>> person email : {}", person.getAccountEmail());

				logger.debug(getEmail(Google.class));
			} catch(Exception e) {
				logger.error(e.toString());
			}
		}
		*/
		return redirectView;
	}

	private <A> String getEmail(Class<A> apiType) {
		try {
			Connection<A> connection = connectionRepository.findPrimaryConnection(apiType);
			String b4 = apiType.getSimpleName();
			//if (apiType.getComponentType() == Google.class) logger.debug("Generic ??");
			//if (apiType.isInstance(Google.class)) {
				logger.debug("------------------------>>>>");
				Google google = (Google)connection.getApi();
				PlusOperations plusOperations = google.plusOperations();
				Person person = plusOperations.getGoogleProfile();
				logger.debug("=======>> person email : {}", person.getAccountEmail());
				return person.getAccountEmail();
			//}
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return null;
	}
}
