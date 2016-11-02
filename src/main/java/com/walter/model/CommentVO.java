package com.walter.model;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

/**
 * Created by yhwang131 on 2016-11-01.
 */
public class CommentVO {

	@Id
	private String _id;

	private int parentPostCd;

	private String userId;

	private String userName;

	private String ip;

	private Date regDt;

	private String comment;

	private List<CommentVO> replys;

	public CommentVO() {
		super();
	}

	public CommentVO(int parentPostCd, String userId, String userName, String ip, Date regDt, String comment) {
		this.parentPostCd = parentPostCd;
		this.userId = userId;
		this.userName = userName;
		this.ip = ip;
		this.regDt = regDt;
		this.comment = comment;
	}

	public CommentVO(String userId, String userName, String ip, Date regDt, String comment) {
		this.userId = userId;
		this.userName = userName;
		this.ip = ip;
		this.regDt = regDt;
		this.comment = comment;
	}

	public String get_id() {
		return _id;
	}

	public int getParentPostCd() {
		return parentPostCd;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getIp() {
		return ip;
	}

	public Date getRegDt() {
		return regDt;
	}

	public String getComment() {
		return comment;
	}

	public List<CommentVO> getReplys() {
		return replys;
	}

	@Override
	public String toString() {
		return "CommentVO{" +
				"_id='" + _id + '\'' +
				", parentPostCd=" + parentPostCd +
				", userId='" + userId + '\'' +
				", userName='" + userName + '\'' +
				", ip='" + ip + '\'' +
				", regDt=" + regDt +
				", comment='" + comment + '\'' +
				", replys=" + replys +
				'}';
	}
}
