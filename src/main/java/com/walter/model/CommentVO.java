package com.walter.model;

import java.util.Date;
import java.util.List;

/**
 * Created by yhwang131 on 2016-11-01.
 */
public class CommentVO {

	private int seq;

	private String userId;

	private String userName;

	private String ip;

	private Date regDt;

	private String comment;

	private List<CommentVO> replys;

	public CommentVO() {
		super();
	}

	public CommentVO(String userId, String userName, String ip, Date regDt, String comment) {
		this.userId = userId;
		this.userName = userName;
		this.ip = ip;
		this.regDt = regDt;
		this.comment = comment;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
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
				"seq='" + seq + '\'' +
				", userId='" + userId + '\'' +
				", userName='" + userName + '\'' +
				", ip='" + ip + '\'' +
				", regDt=" + regDt +
				", comment='" + comment + '\'' +
				", replys=" + replys +
				'}';
	}
}
