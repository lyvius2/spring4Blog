package com.walter.config;

import com.walter.config.authentication.SignInAuthenticationProvider;
import com.walter.config.authentication.SignInFailureHandler;
import com.walter.config.authentication.SignInSuccessHandler;
import com.walter.config.authentication.SignInUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.util.Arrays;

/**
 * 향후 web.xml을 JAVA Config로 전환하면서 주석 삭제 예정
 */
@Configuration
@PropertySource("classpath:/application.properties")/*
@EnableWebSecurity */
public class SecurityConfig /*extends WebSecurityConfigurerAdapter*/ {

	@Value("${admin.email}")
	private String adminEmail;
/*
	@Autowired
	private AuthenticationSuccessHandler signInSuccessHandler;

	@Autowired
	private AuthenticationFailureHandler signInFailureHandler;

	@Autowired
	private AuthenticationProvider signInAuthenticationProvider;

	@Autowired
	private UserDetailsService signInUserDetailsService;

	@Bean
	public ShaPasswordEncoder passwordEncoder() {
		return new ShaPasswordEncoder(512);
	}

	@Bean
	public SignInAuthenticationProvider signInAuthenticationProvider() {
		return new SignInAuthenticationProvider();
	}
*/
	@Bean
	public SignInUserDetailsService signInUserDetailsService() {
		SignInUserDetailsService signInUserDetailsService = new SignInUserDetailsService();
		signInUserDetailsService.setAdminEmail(this.adminEmail);
		return signInUserDetailsService;
	}
/*
	@Bean
	public SignInSuccessHandler signInSuccessHandler() {
		SignInSuccessHandler signInSuccessHandler = new SignInSuccessHandler();
		signInSuccessHandler.setDefaultUrl("/");
		signInSuccessHandler.setRefererUse(false);
		return signInSuccessHandler;
	}

	@Bean
	public SignInFailureHandler signInFailureHandler() {
		SignInFailureHandler signInFailureHandler = new SignInFailureHandler();
		signInFailureHandler.setId("username");
		signInFailureHandler.setPw("password");
		signInFailureHandler.setDefaultFailureUrl("/");
		signInFailureHandler.setErrorMsg("errorMsg");
		return signInFailureHandler;
	}

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return new ProviderManager(Arrays.asList((AuthenticationProvider) signInAuthenticationProvider));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super.configure(http);
		http
				.headers()
					.frameOptions().sameOrigin()
					.and()
				.csrf().disable()
				.authorizeRequests()
					.antMatchers("/post/register**", "/config**").hasRole("ROLE_ADMIN")
					.antMatchers("/resume**").access("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_RECRUITER')")
					.and()
				.exceptionHandling().accessDeniedPage("/403")
					.and()
				.formLogin()
					.loginPage("/401")
					.loginProcessingUrl("/signIn")
					.usernameParameter("username")
					.passwordParameter("password")
					.successHandler(signInSuccessHandler)
					.failureHandler(signInFailureHandler)
					.permitAll()
					.and()
				.logout()
					.invalidateHttpSession(true)
					.logoutUrl("/signOut")
					.logoutSuccessUrl("/")
					.deleteCookies("JSESSIONID, SPRING_SECURITY_REMEMBER_ME_COOKIE")
					.and()
				.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.NEVER)
					.invalidSessionUrl("/401")
					.maximumSessions(1)
					.maxSessionsPreventsLogin(true)
					.expiredUrl("/401")
					.and()
				.enableSessionUrlRewriting(false);
	}

	@Override
	protected UserDetailsService userDetailsService() {
		return this.signInUserDetailsService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}
*/
}
