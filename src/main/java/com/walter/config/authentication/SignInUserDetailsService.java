package com.walter.config.authentication;

import com.walter.dao.MemberDao;
import com.walter.model.MemberVO;

import com.walter.model.RoleVO;
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
import org.springframework.social.facebook.api.User;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yhwang131 on 2016-08-26.
 */
public class SignInUserDetailsService implements UserDetailsService {

	public static final Logger logger = LoggerFactory.getLogger(SignInUserDetailsService.class);
	protected final MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	@Autowired
	private MemberDao memberDao;

	private enum ROLE {
		DEFAULT("ROLE_USER"),
		ADMIN("ROLE_ADMIN"),
		ANONYMOUS("ROLE_ANONYMOUS");

		private RoleVO roleVO;

		ROLE(String authority) {
			RoleVO roleVO = new RoleVO();
			roleVO.setAuthority(authority);
			this.roleVO = roleVO;
		}

		public RoleVO getRoleVO() {
			return this.roleVO;
		}

		public List<RoleVO> getRoleList() {
			List<RoleVO> roleVOList = new ArrayList<>();
			roleVOList.add(this.roleVO);
			return roleVOList;
		}
	}

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
		memberVO.setCredentialsNonExpired(
				memberVO.getPw_expire_dt() != null && memberVO.getPw_expire_dt().compareTo(new Date()) >= 0 ? true : false
		);
		memberVO.setEnabled(
				memberVO.getUse_yn() != null && "Y".equals(memberVO.getUse_yn()) ? true : false
		);
		return memberVO;
	}

	public void onAuthenticationBinding(MemberVO memberVO, User facebookUser, HttpSession httpSession) throws NullPointerException {
		memberVO.setUsername(facebookUser.getId());
		memberVO.setEmail(facebookUser.getEmail());
		memberVO.setFirst_name(facebookUser.getFirstName());
		memberVO.setKr_name(facebookUser.getName());
		memberVO.setLast_name(facebookUser.getLastName());
		memberVO.setAuthorities(ROLE.DEFAULT.getRoleList());
		memberVO.setAccountNonExpired(true);
		memberVO.setAccountNonLocked(true);
		memberVO.setCredentialsNonExpired(true);
		memberVO.setEnabled(true);

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				memberVO, null, ROLE.DEFAULT.getRoleList()
		);
		//authenticationToken.setDetails(memberVO);
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	}

}
