package com.walter.model;

import com.walter.config.CustomStringUtils;

import java.util.Date;

/**
 * Created by yhwang131 on 2017-06-22.
 */
public class ActVO {
	private String title;
	private String sub_title;
	private String description;
	private String tech;
	private String related_site;
	private Date start_dt;
	private Date end_dt;
	private String str_start_dt;
	private String str_end_dt;

	public ActVO() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSub_title() {
		return sub_title;
	}

	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTech() {
		return tech;
	}

	public void setTech(String tech) {
		this.tech = tech;
	}

	public String getRelated_site() {
		return related_site;
	}

	public void setRelated_site(String related_site) {
		this.related_site = related_site;
	}

	public Date getStart_dt() {
		return start_dt;
	}

	public void setStart_dt(Date start_dt) {
		this.start_dt = start_dt;
	}

	public Date getEnd_dt() {
		return end_dt;
	}

	public void setEnd_dt(Date end_dt) {
		this.end_dt = end_dt;
	}

	public String getStr_start_dt() {
		str_start_dt = CustomStringUtils.dateToString(this.start_dt, "yyyy-MM-dd");
		return str_start_dt;
	}

	public String getStr_end_dt() {
		str_end_dt = CustomStringUtils.dateToString(this.end_dt, "yyyy-MM-dd");
		return str_end_dt;
	}
}