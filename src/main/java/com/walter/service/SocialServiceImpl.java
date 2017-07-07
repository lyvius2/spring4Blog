package com.walter.service;

import com.walter.model.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.github.api.GitHub;
import org.springframework.social.github.api.GitHubUserProfile;
import org.springframework.social.github.api.UserOperations;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.api.plus.PlusOperations;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.api.LinkedInProfile;
import org.springframework.social.linkedin.api.ProfileOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by yhwang131 on 2017-06-26.
 */
@Slf4j
@Service
public class SocialServiceImpl implements SocialService {

	@Resource(name="inMemoryConnectionRepository")
	private ConnectionRepository connectionRepository;

	@Override
	public MemberVO bindingSocialUserInfo(String socialType) {
		try {
			switch (socialType) {
				case "facebook" :
					Facebook facebook = this.getApi(Facebook.class);
					String [] fields = { "id", "age_range", "email", "first_name", "gender", "last_name", "link",
							"picture", "locale", "name", "third_party_id", "verified" };
					User user = facebook.fetchObject("me", User.class, fields);
					return new MemberVO(user);
				case "google" :
					Google google = this.getApi(Google.class);
					PlusOperations plusOperations = google.plusOperations();
					Person person = plusOperations.getGoogleProfile();
					return new MemberVO(person);
				case "github" :
					GitHub gitHub = this.getApi(GitHub.class);
					UserOperations userOperations = gitHub.userOperations();
					GitHubUserProfile gitHubUserProfile = userOperations.getUserProfile();
					return new MemberVO(gitHubUserProfile);
				case "linkedin" :
					LinkedIn linkedIn = this.getApi(LinkedIn.class);
					ProfileOperations profileOperations = linkedIn.profileOperations();
					LinkedInProfile linkedInProfile = profileOperations.getUserProfile();
					return new MemberVO(linkedInProfile);
			}
		} catch (Exception e) {
			log.error(e.toString());
		}
		return null;
	}

	private <T> T getApi(Class<T> socialApiType) {
		Connection<T> connection = connectionRepository.findPrimaryConnection(socialApiType);
		//connection.createData().getImageUrl();
		return connection.getApi();
	}
}
