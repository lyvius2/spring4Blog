package com.walter.model;

import java.util.Date;

/**
 * Created by yhwang131 on 2016-09-13.
 */
public class CategoryVO {

	private int category_cd;
	private int parent_category_cd;
	private int depth;
	private String category_name;
	private String access_role;
	private String access_role_name;
	private boolean use_yn;
	private Date reg_dt;
	private String reg_id;
	private Date mod_dt;
	private String mod_id;
	private int order_no;

	public int getCategory_cd() {
		return category_cd;
	}

	public void setCategory_cd(int category_cd) {
		this.category_cd = category_cd;
	}

	public int getParent_category_cd() {
		return parent_category_cd;
	}

	public void setParent_category_cd(int parent_category_cd) {
		this.parent_category_cd = parent_category_cd;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getAccess_role() {
		return access_role;
	}

	public void setAccess_role(String access_role) {
		this.access_role = access_role;
	}

	public String getAccess_role_name() {
		return access_role_name;
	}

	public void setAccess_role_name(String access_role_name) {
		this.access_role_name = access_role_name;
	}

	public boolean isUse_yn() {
		return use_yn;
	}

	public void setUse_yn(boolean use_yn) {
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

	public int getOrder_no() {
		return order_no;
	}

	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}

	@Override
	public String toString() {
		return "CategoryVO{" +
				"category_cd=" + category_cd +
				", parent_category_cd=" + parent_category_cd +
				", depth=" + depth +
				", category_name='" + category_name + '\'' +
				", access_role='" + access_role + '\'' +
				", access_role_name='" + access_role_name + '\'' +
				", use_yn=" + use_yn +
				", reg_dt=" + reg_dt +
				", reg_id='" + reg_id + '\'' +
				", mod_dt=" + mod_dt +
				", mod_id='" + mod_id + '\'' +
				", order_no=" + order_no +
				'}';
	}
}
