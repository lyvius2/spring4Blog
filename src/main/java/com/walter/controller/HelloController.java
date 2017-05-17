package com.walter.controller;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * Created by Walter on 2017-05-16.
 */
@Controller
@RequestMapping("/fb")
public class HelloController {

	@Resource(name="inMemoryConnectionRepository")
	private ConnectionRepository connectionRepository;

	@GetMapping
	public String helloFacebook(Model model) {
		Connection<Facebook> connection = connectionRepository.findPrimaryConnection(Facebook.class);
		if (connection == null) {
			return "redirect:/connect/facebook";
		}

		Facebook facebook = connection.getApi();
		String [] fields = { "id", "age_range", "email", "first_name", "gender",
				"last_name", "link", "locale", "name", "third_party_id", "verified" };

		User userProfile = facebook.fetchObject("me", User.class, fields);
		//model.addAttribute("facebookProfile", facebook.userOperations().getUserProfile());
		model.addAttribute("facebookProfile", userProfile);
		PagedList<Post> feed = facebook.feedOperations().getFeed();
		model.addAttribute("feed", feed);
		return "/connect/facebookResult";
	}

}
