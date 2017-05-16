package com.walter.controller;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

/**
 * Created by Walter on 2017-05-16.
 */
@Controller
@RequestMapping("/fb")
public class HelloController {

	private ConnectionRepository connectionRepository;

	@Inject
	public HelloController(ConnectionRepository connectionRepository) {
		this.connectionRepository = connectionRepository;
	}

	@GetMapping
	public String helloFacebook(Model model) {
		Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
		Facebook facebook = connection != null ? connection.getApi() : null;

		if (connection == null) {
			return "redirect:/connect/facebook";
		}

		model.addAttribute("facebookProfile", facebook.userOperations().getUserProfile());
		PagedList<Post> feed = facebook.feedOperations().getFeed();
		model.addAttribute("feed", feed);
		return "/connect/facebookResult";
	}

}
