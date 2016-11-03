package com.walter.model;

import com.walter.config.CustomStringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yhwang131 on 2016-11-01.
 */
@Document(collection = "comment")
public class CommentVO {

	@Id
	private String _id;
	@Indexed
	private int postCd;
	private String userId;
	private String userName;
	private String ip;
	private String regDt;
	private String comment;
	private List<ReplyVO> replys;

	public CommentVO() {
		super();
		this.regDt = CustomStringUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm");
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public int getPostCd() {
		return postCd;
	}

	public void setPostCd(int postCd) {
		this.postCd = postCd;
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

	public List<ReplyVO> getReplys() {
		return replys;
	}

	public void setReplys(List<ReplyVO> replys) {
		this.replys = replys;
	}

	public void addReplys(ReplyVO replyVO) {
		if(this.replys == null) {
			this.replys = new ArrayList<>();
		}
		this.replys.add(replyVO);
	}

	@Override
	public String toString() {
		return "CommentVO{" +
				"_id='" + _id + '\'' +
				", postCd=" + postCd +
				", userId='" + userId + '\'' +
				", userName='" + userName + '\'' +
				", ip='" + ip + '\'' +
				", regDt='" + regDt + '\'' +
				", comment='" + comment + '\'' +
				", replys=" + replys +
				'}';
	}
}
