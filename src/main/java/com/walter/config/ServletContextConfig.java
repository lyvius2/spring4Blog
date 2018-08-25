package com.walter.config;

import com.google.gson.Gson;
import com.mornya.lib.springsocialnaver.connect.NaverConnectionFactory;
import com.walter.config.drive.GoogleDriveClientSecret;
import com.walter.config.drive.GoogleDriveUploaderProgress;
import com.walter.config.interceptor.WaltersInterceptor;
import org.apache.lucene.analysis.ko.KoreanAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.github.connect.GitHubConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import javax.servlet.Filter;
import java.nio.charset.Charset;
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
@EnableCaching
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
		registry.addInterceptor(waltersInterceptor()).addPathPatterns("/**").excludePathPatterns("/resources/**", "/connect/**", "/api/**", "/healthCheck");
	}

	@Bean
	public WaltersInterceptor waltersInterceptor() {
		return new WaltersInterceptor();
	}

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();

		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setContentType("text/html; charset=UTF-8");
		//resolver.setOrder(2);
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
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tiles = new TilesConfigurer();
		tiles.setDefinitions(new String[]{
				"/WEB-INF/tiles.xml"
		});
		tiles.setCheckRefresh(true);
		return tiles;
	}

	@Bean
	public TilesViewResolver tilesViewResolver() {
		TilesViewResolver tilesViewResolver = new TilesViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);
		tilesViewResolver.setContentType("text/html; charset=UTF-8");
		tilesViewResolver.setOrder(1);
		return tilesViewResolver;
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
	public ConnectionRepository inMemoryConnectionRepository() {
		InMemoryUsersConnectionRepository usersConnectionRepository = new InMemoryUsersConnectionRepository(connectionFactoryLocator());
		return usersConnectionRepository.createConnectionRepository("walters");
	}

	@Bean
	public KoreanAnalyzer koreanAnalyzer() {
		return new KoreanAnalyzer();
	}

	@Bean
	public TaskScheduler taskScheduler() {
		return new ConcurrentTaskScheduler();
	}

	@Bean
	public HttpMessageConverter<String> responseBodyConverter() {
		return new StringHttpMessageConverter(Charset.forName("UTF-8"));
	}

	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}

	@Bean
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheCacheManager().getObject());
	}

	@Bean
	public EhCacheManagerFactoryBean ehCacheCacheManager() {
		EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
		ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
		ehCacheManagerFactoryBean.setShared(true);
		return ehCacheManagerFactoryBean;
	}
}
