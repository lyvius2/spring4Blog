package com.walter.model;

import com.walter.config.CustomStringUtils;
import lombok.Data;

import java.util.Date;

/**
 * Created by yhwang131 on 2016-11-03.
 */
@Data
public class ReplyVO {

	private String id;
	private String name;
	private String ip;
	private String link;
	private String profile_image_url;
	private String regDt;
	private String comment;

	public ReplyVO() {
		super();
		this.regDt = CustomStringUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm");
	}

	public void setUserData(MemberVO memberVO) {
		if (memberVO != null) {
			this.id = memberVO.getId();
			this.name = memberVO.getName();
			this.link = memberVO.getLink();
			this.profile_image_url = memberVO.getProfile_image_url();
		}
	}
}
