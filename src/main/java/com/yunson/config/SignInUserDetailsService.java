package com.yunson.config;

import com.yunson.firstapp.member.MemberDao;
import com.yunson.firstapp.member.MemberVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by yhwang131 on 2016-08-26.
 */
public class SignInUserDetailsService implements UserDetailsService {

	public static final Logger logger = LoggerFactory.getLogger(SignInUserDetailsService.class);
	protected final MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	@Autowired
	private MemberDao memberDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Sign In... " + username);
		/*
		boolean enabled = false;
		boolean accountNonExpired = false;
		boolean credentialsNonExpired = false;
		boolean accountNonLocked = false;
		*/
		HashMap<String, Object> param = new HashMap<>();
		param.put("username", username);
		MemberVO memberVO = memberDao.getMemberDetail(param);

		System.out.println(memberVO);
		if(memberVO == null) {
			UsernameNotFoundException unfe = new UsernameNotFoundException(messages.getMessage("JdbcDaoImpl.notFound", new Object[]{username}, "User {0} not found."));
			throw unfe;
		}

		Collection<Role> roles = new ArrayList<>();
		Role role = new Role();
		role.setAuthority(memberVO.getPermission());
		roles.add(role);

		memberVO.setAuthorities(roles);
		memberVO.setAccountNonExpired(true);
		memberVO.setAccountNonLocked(true);
		memberVO.setCredentialsNonExpired(true);
		memberVO.setEnabled(memberVO.getUse_yn().equals("Y")?true:false);

		return memberVO;
	}

	private class Role implements GrantedAuthority {
		String authority;

		@Override
		public String getAuthority() {
			return authority;
		}

		public void setAuthority(String authority) {
			this.authority = authority;
		}
	}
}
