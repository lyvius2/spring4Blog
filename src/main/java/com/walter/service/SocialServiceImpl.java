package com.walter.service;

import com.mornya.lib.springsocialnaver.api.Naver;
import com.walter.model.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.social.connect.*;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.social.connect.web.ConnectSupport;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yhwang131 on 2017-06-26.
 */
@Slf4j
@Service
public class SocialServiceImpl implements SocialService {

	private final MultiValueMap<Class<?>, ConnectInterceptor<?>> connectInterceptors = new LinkedMultiValueMap();
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	@Resource(name="inMemoryConnectionRepository")
	private ConnectionRepository connectionRepository;

	@Autowired
	private ConnectionFactoryLocator connectionFactoryLocator;

	@Override
	public MemberVO bindingSocialUserInfo(String socialType, NativeWebRequest request) {
		MemberVO memberVO = null;
		try {
			OAuth2ConnectionFactory<?> connectionFactory = (OAuth2ConnectionFactory)this.connectionFactoryLocator.getConnectionFactory(socialType);
			ConnectSupport connectSupport = new ConnectSupport(this.sessionStrategy);
			Connection<?> connection = connectSupport.completeConnection(connectionFactory, request);
			switch (socialType) {
				case "facebook" :
					Facebook facebook = this.getApi(Facebook.class, connection);
					String [] fields = { "id", "age_range", "email", "first_name", "gender", "last_name", "link",
							"picture", "locale", "name", "third_party_id", "verified" };
					User user = facebook.fetchObject("me", User.class, fields);
					memberVO = new MemberVO(user);
					break;
				case "google" :
					Google google = this.getApi(Google.class, connection);
					PlusOperations plusOperations = google.plusOperations();
					Person person = plusOperations.getGoogleProfile();
					memberVO = new MemberVO(person);
					break;
				case "github" :
					GitHub gitHub = this.getApi(GitHub.class, connection);
					UserOperations userOperations = gitHub.userOperations();
					GitHubUserProfile gitHubUserProfile = userOperations.getUserProfile();
					memberVO = new MemberVO(gitHubUserProfile);
					break;
				case "linkedin" :
					LinkedIn linkedIn = this.getApi(LinkedIn.class, connection);
					ProfileOperations profileOperations = linkedIn.profileOperations();
					LinkedInProfile linkedInProfile = profileOperations.getUserProfile();
					memberVO = new MemberVO(linkedInProfile);
					break;
				case "naver" :
					Naver naver = this.getApi(Naver.class, connection);
					com.mornya.lib.springsocialnaver.api.abstracts.UserOperation naverOperation = naver.userOperation();
					memberVO = new MemberVO(naverOperation);
					break;
			}
			this.connectionRepository.removeConnection(new ConnectionKey(connection.getKey().getProviderId(), connection.getKey().getProviderUserId()));
			this.addConnection(connection, connectionFactory, request);
		} catch (Exception e) {
			this.sessionStrategy.setAttribute(request, "social_provider_error", e);
			log.warn("Exception while handling OAuth2 callback (" + e.getMessage() + "). Redirecting to " + socialType + " connection status page.");
		}
		return memberVO;
	}

	private <T> T getApi(Class<T> T, Connection<?> connection) {
		return (T)connection.getApi();
	}

	private void addConnection(Connection<?> connection, ConnectionFactory<?> connectionFactory, WebRequest request) {
		try {
			this.connectionRepository.addConnection(connection);
			this.postConnect(connectionFactory, connection, request);
		} catch (DuplicateConnectionException var5) {
			this.sessionStrategy.setAttribute(request, "social_addConnection_duplicate", var5);
		}
	}

	private void postConnect(ConnectionFactory<?> connectionFactory, Connection<?> connection, WebRequest request) {
		Iterator i$ = this.interceptingConnectionsTo(connectionFactory).iterator();
		while(i$.hasNext()) {
			ConnectInterceptor interceptor = (ConnectInterceptor)i$.next();
			interceptor.postConnect(connection, request);
		}
	}

	private List<ConnectInterceptor<?>> interceptingConnectionsTo(ConnectionFactory<?> connectionFactory) {
		Class<?> serviceType = GenericTypeResolver.resolveTypeArgument(connectionFactory.getClass(), ConnectionFactory.class);
		List<ConnectInterceptor<?>> typedInterceptors = (List)this.connectInterceptors.get(serviceType);
		if (typedInterceptors == null) {
			typedInterceptors = Collections.emptyList();
		}
		return typedInterceptors;
	}
}
