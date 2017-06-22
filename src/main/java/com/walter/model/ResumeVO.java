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
	private String photo_url;
	private String email;
	private String real_addr;
	private String web_addr;
	private String git_addr;

	private List<ActVO> education;
	private List<ActVO> experience;
	private List<ActVO> project;

	private List<AbilityVO> skill;

	/*public ResumeVO() {
		super();
		experience.add(new ActVO());
		project.add(new ActVO());
	}*/

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

	public String getPhoto_url() {
		return photo_url;
	}

	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
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

	public void setTech(int projectIndex, String tech) {
		this.project.get(projectIndex).setTech(tech);
	}

	@Override
	public String toString() {
		return "ResumeVO{" +
				"_id='" + _id + '\'' +
				", name='" + name + '\'' +
				", eng_name='" + eng_name + '\'' +
				", photo_url='" + photo_url + '\'' +
				", email='" + email + '\'' +
				", real_addr='" + real_addr + '\'' +
				", web_addr='" + web_addr + '\'' +
				", git_addr='" + git_addr + '\'' +
				", education=" + education +
				", experience=" + experience +
				", project=" + project +
				", skill=" + skill +
				'}';
	}



}


