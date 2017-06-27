package com.walter.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.facebook.api.User;
import org.springframework.social.github.api.GitHubUserProfile;
import org.springframework.social.google.api.plus.Person;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * Created by yhwang131 on 2016-08-22.
 */
public class MemberVO implements UserDetails {
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	private int seq;

	@NotNull(message = "필수 입력 값입니다.")
	@Size(min = 4, max = 20, message = "ID는 4글자 이상 20글자 미만으로 지정하십시오.")
	private String username;

	@NotNull(message = "비밀번호는 반드시 입력하십시오.")
	@Size(min = 8, max = 20, message = "비밀번호는 8자리 이상 20자리 미만으로 지정하십시오.")
	@Pattern(regexp = "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-zA-Z]).{8,20}$", message = "영문과 숫자 그리고 특수 문자를 포함하여야 합니다.")
	private String password;

	@Pattern(regexp = "^[가-힣]*$", message = "한글로 입력하십시오.")
	private String kr_name;

	private String first_name;
	private String last_name;
	private String email;
	private String phone;
	private String use_yn;
	private Date reg_dt;
	private String reg_id;
	private Date mod_dt;
	private String mod_id;
	private String permission;
	private String nationality;
	private String nationality_name;
	private Date pw_expire_dt;

	private String social_type;
	private String id;
	private String link;
	private String profile_image_url;

	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	private Collection<? extends GrantedAuthority> authorities;

	public MemberVO() {
		super();
	}

	/**
	 * Facebook 사용자 정보 매핑
	 * @param user
	 */
	public MemberVO(User user) {
		this.username = user.getName();
		this.first_name = user.getFirstName();
		this.last_name = user.getLastName();
		this.email = user.getEmail();
		this.id = user.getId();
		this.link = user.getLink();
		this.social_type = "facebook";
		this.profile_image_url = ((LinkedHashMap)((LinkedHashMap)user.getExtraData().get("picture")).get("data")).get("url").toString();
	}

	/**
	 * Google 사용자 정보 매핑
	 * @param person
	 */
	public MemberVO(Person person) {
		this.username = person.getDisplayName();
		this.first_name = person.getGivenName();
		this.last_name = person.getFamilyName();
		this.email = person.getAccountEmail();
		this.id = person.getId();
		this.link = person.getUrl();
		this.social_type = "google";
		this.profile_image_url = person.getImageUrl();
	}

	/**
	 * GitHub 사용자 정보 매핑
	 * @param gitHubUserProfile
	 */
	public MemberVO(GitHubUserProfile gitHubUserProfile) {
		this.username = gitHubUserProfile.getName();
		this.email = gitHubUserProfile.getEmail();
		this.id = Long.toString(gitHubUserProfile.getId());
		this.link = "https://github.com/" + this.username;
		this.social_type = "github";
		this.profile_image_url = gitHubUserProfile.getAvatarUrl();
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getKr_name() {
		return kr_name;
	}

	public void setKr_name(String kr_name) {
		this.kr_name = kr_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUse_yn() {
		return use_yn;
	}

	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}

	public Date getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}

	public String getReg_id() {
		return reg_id;
	}

	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}

	public Date getMod_dt() {
		return mod_dt;
	}

	public void setMod_dt(Date mod_dt) {
		this.mod_dt = mod_dt;
	}

	public String getMod_id() {
		return mod_id;
	}

	public void setMod_id(String mod_id) {
		this.mod_id = mod_id;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getNationality_name() {
		return nationality_name;
	}

	public void setNationality_name(String nationality_name) {
		this.nationality_name = nationality_name;
	}

	public Date getPw_expire_dt() {
		return pw_expire_dt;
	}

	public void setPw_expire_dt(Date pw_expire_dt) {
		this.pw_expire_dt = pw_expire_dt;
	}

	public String getSocial_type() {
		return social_type;
	}

	public void setSocial_type(String social_type) {
		this.social_type = social_type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getProfile_image_url() {
		return profile_image_url;
	}

	public void setProfile_image_url(String profile_image_url) {
		this.profile_image_url = profile_image_url;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String toString() {
		return "MemberVO{" +
				"seq=" + seq +
				", username='" + username + '\'' +
				", kr_name='" + kr_name + '\'' +
				", first_name='" + first_name + '\'' +
				", last_name='" + last_name + '\'' +
				", email='" + email + '\'' +
				", phone='" + phone + '\'' +
				", use_yn='" + use_yn + '\'' +
				", reg_dt=" + reg_dt +
				", reg_id='" + reg_id + '\'' +
				", mod_dt=" + mod_dt +
				", mod_id='" + mod_id + '\'' +
				", permission='" + permission + '\'' +
				", nationality='" + nationality + '\'' +
				", nationality_name='" + nationality_name + '\'' +
				", accountNonExpired=" + accountNonExpired +
				", accountNonLocked=" + accountNonLocked +
				", credentialsNonExpired=" + credentialsNonExpired +
				", enabled=" + enabled +
				", authorities=" + authorities +
				'}';
	}
}
