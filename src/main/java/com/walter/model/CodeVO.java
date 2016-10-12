package com.walter.model;

import java.util.Date;

/**
 * Created by yhwang131 on 2016-08-24.
 */
public class CodeVO {

	private String up_cd;
	private String cd;
	private String cd_name;
	private String use_yn;
	private Date reg_dt;
	private String reg_id;
	private Date mod_dt;
	private String mod_id;

	public CodeVO() {
		super();
	}

	public CodeVO(String up_cd) {
		super();
		this.up_cd = up_cd;
	}

	public String getUp_cd() {
		return up_cd;
	}

	public void setUp_cd(String up_cd) {
		this.up_cd = up_cd;
	}

	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

	public String getCd_name() {
		return cd_name;
	}

	public void setCd_name(String cd_name) {
		this.cd_name = cd_name;
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

	@Override
	public String toString() {
		return "CodeVO{" +
				"up_cd='" + up_cd + '\'' +
				", cd='" + cd + '\'' +
				", cd_name='" + cd_name + '\'' +
				", use_yn='" + use_yn + '\'' +
				", reg_dt=" + reg_dt +
				", reg_id='" + reg_id + '\'' +
				", mod_dt=" + mod_dt +
				", mod_id='" + mod_id + '\'' +
				'}';
	}
}
