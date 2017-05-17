package com.walter.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by yhwang131 on 2017-05-17.
 */
public class SignInAuthenticationProvider implements AuthenticationProvider {
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	protected final MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	@Autowired
	private ShaPasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UserDetails userDetails;

		try {
			String username = authentication.getName();
			userDetails = userDetailsService.loadUserByUsername(username);

			String hashedPassword = passwordEncoder.encodePassword(authentication.getCredentials().toString(), null);
			if (!hashedPassword.equals(userDetails.getPassword())) throw new BadCredentialsException(messages.getMessage("PasswordComparisonAuthenticator.badCredentials"));
			if (!userDetails.isCredentialsNonExpired()) throw new BadCredentialsException(messages.getMessage("LdapAuthenticationProvider.expired"));
			if (!userDetails.isEnabled()) throw new BadCredentialsException(messages.getMessage("LdapAuthenticationProvider.disabled"));
		} catch (UsernameNotFoundException e) {
			logger.error(e.toString());
			throw new UsernameNotFoundException(e.getMessage());
		} catch (BadCredentialsException e) {
			logger.error(e.toString());
			throw new BadCredentialsException(e.getMessage());
		} catch (Exception e) {
			logger.error(e.toString());
			throw new RuntimeException(e.getMessage());
		}

		return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
