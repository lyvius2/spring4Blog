package com.walter.config.code;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Walter on 2017-07-09.
 */
public enum Role {
	DEFAULT("ROLE_USER"),
	ADMIN("ROLE_ADMIN"),
	RECRUITER("ROLE_RECRUITER");

	private List<SimpleGrantedAuthority> authority = new ArrayList<>();

	Role(String role) {
		authority.add(new SimpleGrantedAuthority("ROLE_USER"));
		if (!role.equals("ROLE_USER")) authority.add(new SimpleGrantedAuthority(role));
	}

	public List<SimpleGrantedAuthority> getRoleList() {
		return authority;
	}
}
