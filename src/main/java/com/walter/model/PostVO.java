package com.walter.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by yhwang131 on 2016-10-11.
 */
public class PostVO {

	private int post_cd;

	@NotNull(message = "제목은 반드시 입력하세요.")
	@Size(max = 100, message = "50글자 이하로 입력하십시오.")
	private String title;

	private StringBuffer content;
	private int category_cd;
	private Boolean use_yn;
	private Boolean comment_yn;
	private Date reg_dt;
	private String reg_id;
	private Date mod_dt;
	private String mod_id;
	private StringBuffer delegate_img;

	public PostVO() {
		super();
	}

	public PostVO(Boolean use_yn, Boolean comment_yn) {
		super();
		this.use_yn = use_yn;
		this.comment_yn = comment_yn;
	}

	public int getPost_cd() {
		return post_cd;
	}

	public void setPost_cd(int post_cd) {
		this.post_cd = post_cd;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public StringBuffer getContent() {
		return content;
	}

	public void setContent(StringBuffer content) {
		this.content = content;
	}

	public int getCategory_cd() {
		return category_cd;
	}

	public void setCategory_cd(int category_cd) {
		this.category_cd = category_cd;
	}

	public Boolean getUse_yn() {
		return use_yn;
	}

	public void setUse_yn(Boolean use_yn) {
		this.use_yn = use_yn;
	}

	public Boolean getComment_yn() {
		return comment_yn;
	}

	public void setComment_yn(Boolean comment_yn) {
		this.comment_yn = comment_yn;
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

	public StringBuffer getDelegate_img() {
		return delegate_img;
	}

	public void setDelegate_img(StringBuffer delegate_img) {
		this.delegate_img = delegate_img;
	}

	@Override
	public String toString() {
		return "PostVO{" +
				"post_cd=" + post_cd +
				", title='" + title + '\'' +
				", content=" + content +
				", category_cd=" + category_cd +
				", use_yn=" + use_yn +
				", comment_yn=" + comment_yn +
				", reg_dt=" + reg_dt +
				", reg_id='" + reg_id + '\'' +
				", mod_dt=" + mod_dt +
				", mod_id='" + mod_id + '\'' +
				", delegate_img=" + delegate_img +
				'}';
	}
}