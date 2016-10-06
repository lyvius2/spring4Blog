package com.walter.config;

import com.walter.dao.MemberDao;
import com.walter.model.MemberVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Date;
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
		HashMap<String, Object> param = new HashMap<>();
		param.put("username", username);
		MemberVO memberVO = memberDao.getMemberDetail(param);

		if (memberVO == null) {
			UsernameNotFoundException unfe = new UsernameNotFoundException(messages.getMessage("JdbcDaoImpl.notFound", new Object[]{username}, "User {0} not found"));
			throw unfe;
		}

		memberVO.setAuthorities(memberDao.getRoleList(username));
		memberVO.setAccountNonExpired(true);
		memberVO.setAccountNonLocked(true);
		memberVO.setCredentialsNonExpired(memberVO.getPw_expire_dt().compareTo(new Date())>=0 ? true : false);
		memberVO.setEnabled("Y".equals(memberVO.getUse_yn()) ? true : false);

		return memberVO;
	}
}