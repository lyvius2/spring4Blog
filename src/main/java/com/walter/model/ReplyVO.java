package com.walter.model;

import com.walter.config.CustomStringUtils;

import java.util.Date;

/**
 * Created by yhwang131 on 2016-11-03.
 */
public class ReplyVO {

	private String userId;
	private String userName;
	private String ip;
	private String regDt;
	private String comment;
	private String targetUserName;

	public ReplyVO() {
		super();
		this.regDt = CustomStringUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm");
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getTargetUserName() {
		return targetUserName;
	}

	public void setTargetUserName(String targetUserName) {
		this.targetUserName = targetUserName;
	}

	public void setUserData(MemberVO memberVO) {
		this.userId = memberVO!=null?memberVO.getUsername():"Anonymous";
		this.userName = memberVO!=null?memberVO.getKr_name():"아무개";
	}

	@Override
	public String toString() {
		return "ReplyVO{" +
				"userId='" + userId + '\'' +
				", userName='" + userName + '\'' +
				", ip='" + ip + '\'' +
				", regDt='" + regDt + '\'' +
				", comment='" + comment + '\'' +
				", targetUserName='" + targetUserName + '\'' +
				'}';
	}
}
