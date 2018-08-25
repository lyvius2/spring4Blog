package com.walter;

import com.walter.config.*;
import com.walter.config.filter.CorsFilter;
import com.walter.config.filter.CrossSiteScriptingFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import java.util.EnumSet;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	public void onStartup(ServletContext container) throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation("com.walter.config");

		container.addListener(new ContextLoaderListener(context));
		container.addListener(new SessionListener());
		container.addListener(new HttpSessionEventPublisher());

		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true);
		FilterRegistration.Dynamic characterEncoding = container.addFilter("encodingFilter", encodingFilter);
		characterEncoding.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");

		ServletRegistration.Dynamic dispatcher = container.addServlet("appServlet", new DispatcherServlet(context));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		dispatcher.setMultipartConfig(new MultipartConfigElement("/tmp", 1024*1024*5, 1024*1024*6*5, 1024*1024));
		//super.onStartup(container);
		registerDispatcherServlet(container);
	}

	@Override
	protected void registerDispatcherServlet(ServletContext container) {
		super.registerDispatcherServlet(container);
		//container.addListener(new HttpSessionEventPublisher());
	}

	@Override
	protected Filter[] getServletFilters() {
		CrossSiteScriptingFilter xssFilter = new CrossSiteScriptingFilter();
		CorsFilter corsFilter = new CorsFilter();
		DelegatingFilterProxy springSecurityFilterChain = new DelegatingFilterProxy();
		return new Filter[]{xssFilter, corsFilter, springSecurityFilterChain};
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{ServletContextConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{RootConfig.class, SecurityConfig.class, MyBatisConfig.class, JpaConfig.class, MongodbConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}
}
