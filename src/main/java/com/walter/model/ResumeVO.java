package com.walter.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yhwang131 on 2017-06-20.
 */
@Document(collection = "resume")
public class ResumeVO {

	@Id
	@Indexed
	private String _id;

	private String name;
	private String eng_name;
	private String email;
	private String real_addr;
	private String web_addr;
	private String git_addr;
	private String image_url;

	private List<ActVO> education = new ArrayList<>();
	private List<ActVO> experience = new ArrayList<>();
	private List<ActVO> project = new ArrayList<>();

	private List<AbilityVO> skill = new ArrayList<>();

	private Date last_saved_date;

	public ResumeVO() {
		super();
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEng_name() {
		return eng_name;
	}

	public void setEng_name(String eng_name) {
		this.eng_name = eng_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getReal_addr() {
		return real_addr;
	}

	public void setReal_addr(String real_addr) {
		this.real_addr = real_addr;
	}

	public String getWeb_addr() {
		return web_addr;
	}

	public void setWeb_addr(String web_addr) {
		this.web_addr = web_addr;
	}

	public String getGit_addr() {
		return git_addr;
	}

	public void setGit_addr(String git_addr) {
		this.git_addr = git_addr;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public List<ActVO> getEducation() {
		return education;
	}

	public void setEducation(List<ActVO> education) {
		this.education = education;
	}

	public List<ActVO> getExperience() {
		return experience;
	}

	public void setExperience(List<ActVO> experience) {
		this.experience = experience;
	}

	public List<ActVO> getProject() {
		return project;
	}

	public void setProject(List<ActVO> project) {
		this.project = project;
	}

	public List<AbilityVO> getSkill() {
		return skill;
	}

	public void setSkill(List<AbilityVO> skill) {
		this.skill = skill;
	}

	public Date getLast_saved_date() {
		return last_saved_date;
	}

	public void setLast_saved_date(Date last_saved_date) {
		this.last_saved_date = last_saved_date;
	}

	public void setTech(int projectIndex, String tech) {
		this.project.get(projectIndex).setTech(tech);
	}

	@Override
	public String toString() {
		return "ResumeVO{" +
				"_id='" + _id + '\'' +
				", name='" + name + '\'' +
				", eng_name='" + eng_name + '\'' +
				", email='" + email + '\'' +
				", real_addr='" + real_addr + '\'' +
				", web_addr='" + web_addr + '\'' +
				", git_addr='" + git_addr + '\'' +
				", image_url='" + image_url + '\'' +
				", education=" + education +
				", experience=" + experience +
				", project=" + project +
				", skill=" + skill +
				", last_saved_date=" + last_saved_date +
				'}';
	}
}


