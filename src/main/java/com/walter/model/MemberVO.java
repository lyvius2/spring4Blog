package com.walter.model;

import com.walter.config.code.Role;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.facebook.api.User;
import org.springframework.social.github.api.GitHubUserProfile;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.linkedin.api.LinkedInProfile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * Created by yhwang131 on 2016-08-22.
 */
@Data
@RequiredArgsConstructor
public class MemberVO implements UserDetails {
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	private int seq;

	@NotNull(message = "필수 입력 값입니다.")
	@Size(min = 4, max = 50, message = "ID는 4글자 이상 50글자 미만으로 지정하십시오.")
	private String username;

	@NotNull(message = "비밀번호는 반드시 입력하십시오.")
	@Size(min = 8, max = 20, message = "비밀번호는 8자리 이상 20자리 미만으로 지정하십시오.")
	@Pattern(regexp = "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", message = "영문과 숫자 그리고 특수 문자를 포함하여야 합니다.")
	private String password;

	@NotNull(message = "필수 입력 값입니다.")
	private String name;

	private boolean use_yn = true;
	private Date reg_dt;
	private String reg_id;
	private Date mod_dt;
	private String mod_id;

	private String social_type;
	private String id;
	private String link;
	private String profile_image_url;

	private boolean accountNonExpired = true;
	private boolean accountNonLocked = true;
	private boolean credentialsNonExpired = true;
	private boolean enabled;
	private Collection<? extends GrantedAuthority> authorities;

	/**
	 * Facebook 사용자 정보 매핑
	 * @param user
	 */
	public MemberVO(User user) {
		this.name = user.getName();
		this.username = user.getEmail();
		this.id = user.getId();
		this.link = user.getLink();
		this.social_type = "facebook";
		this.profile_image_url = ((LinkedHashMap)((LinkedHashMap)user.getExtraData().get("picture")).get("data")).get("url").toString();
		this.authorities = Role.DEFAULT.getRoleList();
	}

	/**
	 * Google 사용자 정보 매핑
	 * @param person
	 */
	public MemberVO(Person person) {
		this.name = person.getDisplayName();
		this.username = person.getAccountEmail();
		this.id = person.getId();
		this.link = person.getUrl();
		this.social_type = "google";
		this.profile_image_url = person.getImageUrl();
		this.authorities = Role.DEFAULT.getRoleList();
	}

	/**
	 * GitHub 사용자 정보 매핑
	 * @param gitHubUserProfile
	 */
	public MemberVO(GitHubUserProfile gitHubUserProfile) {
		this.name = gitHubUserProfile.getName();
		this.username = gitHubUserProfile.getEmail();
		this.id = Long.toString(gitHubUserProfile.getId());
		this.link = "https://github.com/" + gitHubUserProfile.getLogin();
		this.social_type = "github";
		this.profile_image_url = gitHubUserProfile.getAvatarUrl();
		this.authorities = Role.DEFAULT.getRoleList();
	}

	/**
	 * LinkedIn 사용자 정보 매핑
	 * @param linkedInProfile
	 */
	public MemberVO(LinkedInProfile linkedInProfile) {
		this.name = StringUtils.stripToEmpty(linkedInProfile.getFirstName()) + " " + StringUtils.stripToEmpty(linkedInProfile.getLastName());
		this.username = linkedInProfile.getEmailAddress();
		this.id = linkedInProfile.getId();
		this.link = linkedInProfile.getPublicProfileUrl();
		this.social_type = "linkedin";
		this.profile_image_url = linkedInProfile.getProfilePictureUrl();
		this.authorities = Role.RECRUITER.getRoleList();
	}
}
