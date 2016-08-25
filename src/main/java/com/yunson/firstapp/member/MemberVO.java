package com.yunson.firstapp.member;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by yhwang131 on 2016-08-22.
 */
public class MemberVO {

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

	@Override
	public String toString() {
		return "MemberVO{" +
				"seq=" + seq +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
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
				'}';
	}
}
