package com.walter.config.authentication;

import com.walter.config.code.Role;
import com.walter.dao.MemberDao;
import com.walter.model.MemberVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashMap;

/**
 * Created by yhwang131 on 2016-08-26.
 */
public class SignInUserDetailsService implements UserDetailsService {
	public static final Logger logger = LoggerFactory.getLogger(SignInUserDetailsService.class);
	protected final MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	private String adminEmail;

	@Autowired
	private MemberDao memberDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		HashMap<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("username", username);
		MemberVO memberVO = memberDao.getMember(paramsMap);

		if (memberVO == null) {
			UsernameNotFoundException unfe = new UsernameNotFoundException(messages.getMessage("JdbcDaoImpl.notFound", new Object[]{username}, "User {0} not found"));
			throw unfe;
		}

		memberVO.setAuthorities(Role.ADMIN.getRoleList());
		memberVO.setEnabled(memberVO.isUse_yn());
		return memberVO;
	}

	public void onAuthenticationWithSocial(MemberVO memberVO) {
		HashMap<String, Object> paramsMap = new HashMap<>();
		paramsMap.put("username", memberVO.getUsername());
		MemberVO existMember = memberDao.getMember(paramsMap);
		if (existMember != null) {
			memberVO.setAuthorities(Role.ADMIN.getRoleList());
			if (existMember.getProfile_image_url() == null
					|| !existMember.getProfile_image_url().equals(memberVO.getProfile_image_url())) {
				MemberVO paramsMember = new MemberVO();
				paramsMember.setUsername(memberVO.getUsername());
				paramsMember.setMod_id(memberVO.getUsername());
				paramsMember.setProfile_image_url(memberVO.getProfile_image_url());
				paramsMember.setUse_yn(true);
				memberDao.modMember(paramsMember);
			}
		} else if (memberVO.getUsername().equals(adminEmail)) {
			memberVO.setAuthorities(Role.ADMIN.getRoleList());
		}
		memberVO.setEnabled(memberVO.isUse_yn());

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				memberVO, null, memberVO.getAuthorities()
		);
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
}
