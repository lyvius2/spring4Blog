package com.walter.controller;

import org.jboss.logging.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.view.RedirectView;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

/**
 * Created by yhwang131 on 2017-05-17.
 */
@Controller
@RequestMapping("/connect")
public class FacebookTestController extends ConnectController {
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	public FacebookTestController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
		super(connectionFactoryLocator, connectionRepository);
	}

	@RequestMapping(value="/{providerId}", method= RequestMethod.GET, params="code")
	public RedirectView oauth2Callback(@PathVariable String providerId, NativeWebRequest request) {
		logger.debug("Code22 : " + request.getParameter("code"));
		Iterator<String> ttt = request.getParameterNames();
		while (ttt.hasNext()) {
			logger.debug("---------->> " + ttt.next());
		}
		((HttpServletRequest)request.getNativeRequest()).getSession().setAttribute("code", request.getParameter("code"));
		return super.oauth2Callback(providerId, request);
	}
}
