package com.walter.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yhwang131 on 2017-06-20.
 */
@Data
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

	@Field("last_saved_date")
	private Date lastSavedDate;

	public void setTech(int projectIndex, String tech) {
		this.project.get(projectIndex).setTech(tech);
	}
}


