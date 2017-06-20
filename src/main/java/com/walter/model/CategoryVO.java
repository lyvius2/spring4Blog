package com.walter.model;

import javax.annotation.Nullable;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by yhwang131 on 2016-09-13.
 */
public class CategoryVO implements Serializable {

	private int category_cd;

	@Nullable
	@Size(min = 2, max = 48, message = "2자 이상 24자 이하로 입력하세요.")
	private String category_name;

	private boolean use_yn;
	private Date reg_dt;
	private String reg_id;
	private Date mod_dt;
	private String mod_id;
	private int order_no;

	public CategoryVO(){
		super();
	}

	public int getCategory_cd() {
		return category_cd;
	}

	public void setCategory_cd(int category_cd) {
		this.category_cd = category_cd;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
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
				", category_name='" + category_name + '\'' +
				", use_yn=" + use_yn +
				", reg_dt=" + reg_dt +
				", reg_id='" + reg_id + '\'' +
				", mod_dt=" + mod_dt +
				", mod_id='" + mod_id + '\'' +
				", order_no=" + order_no +
				'}';
	}
}
