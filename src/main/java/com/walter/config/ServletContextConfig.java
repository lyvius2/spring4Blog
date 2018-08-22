package com.walter.config;

import com.google.gson.Gson;
import com.mornya.lib.springsocialnaver.connect.NaverConnectionFactory;
import com.walter.config.drive.GoogleDriveClientSecret;
import com.walter.config.drive.GoogleDriveUploaderProgress;
import com.walter.config.interceptor.WaltersInterceptor;
import org.apache.lucene.analysis.ko.KoreanAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.github.connect.GitHubConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * ApplicationContext Setting JAVA Config
 * Created by Walter on 2018-08-23.
 */
@Configuration
@ComponentScan(basePackages = {"com.walter.config", "com.walter.dao", "com.walter.controller", "com.walter.service", "com.walter.util"})
@PropertySource("classpath:/application.properties")
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableAspectJAutoProxy
@EnableScheduling
@EnableWebMvc
public class ServletContextConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private Environment env;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/robots.txt").addResourceLocations("/robots.txt");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new WaltersInterceptor()).addPathPatterns("/**").excludePathPatterns("/resources/**", "/connect/**", "/api/**", "/healthCheck");
	}

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views");
		resolver.setSuffix(".jsp");
		resolver.setContentType("text/html; charset=UTF-8");
		resolver.setOrder(1);
		return resolver;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public Gson gson() {
		return new Gson();
	}

	@Bean
	public GoogleDriveUploaderProgress googleDriveUploaderProgress() {
		GoogleDriveUploaderProgress progress = new GoogleDriveUploaderProgress();
		progress.setUploadFolderName("blog images");
		return progress;
	}

	@Bean
	public ConnectionFactoryRegistry connectionFactoryLocator() {
		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
		List<ConnectionFactory<?>> factories = new ArrayList<>();
		factories.add(new FacebookConnectionFactory(env.getProperty("spring.social.facebook.appId"), env.getProperty("spring.social.facebook.appSecret")));
		factories.add(new GoogleConnectionFactory(env.getProperty("spring.social.google.appId"), env.getProperty("spring.social.google.appSecret")));
		factories.add(new GitHubConnectionFactory(env.getProperty("spring.social.github.appId"), env.getProperty("spring.social.github.appSecret")));
		factories.add(new LinkedInConnectionFactory(env.getProperty("spring.social.linkedin.appId"), env.getProperty("spring.social.linkedin.appSecret")));
		factories.add(new NaverConnectionFactory(env.getProperty("spring.social.naver.appId"), env.getProperty("spring.social.naver.appSecret")));
		registry.setConnectionFactories(factories);
		return registry;
	}

	@Bean
	public GoogleDriveClientSecret googleDriveClientSecret() {
		GoogleDriveClientSecret secret = new GoogleDriveClientSecret();
		secret.setClient_id(env.getProperty("google.drive.api.client_id"));
		secret.setProject_id(env.getProperty("google.drive.api.project_id"));
		secret.setAuth_uri(env.getProperty("google.drive.api.auth_uri"));
		secret.setToken_uri(env.getProperty("google.drive.api.token_uri"));
		secret.setAuth_provider_x509_cert_url(env.getProperty("google.drive.api.auth_provider_x509_cert_url"));
		secret.setClient_secret(env.getProperty("google.drive.api.client_secret"));
		secret.setRedirect_uris(env.getProperty("google.drive.api.redirect_uris"));
		return secret;
	}

	@Bean
	public InMemoryUsersConnectionRepository inMemoryUsersConnectionRepository() {
		return new InMemoryUsersConnectionRepository(connectionFactoryLocator());
	}

	@Bean
	public KoreanAnalyzer koreanAnalyzer() {
		return new KoreanAnalyzer();
	}
}
