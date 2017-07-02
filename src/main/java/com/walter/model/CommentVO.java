package com.walter.model;

import com.walter.config.CustomStringUtils;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yhwang131 on 2016-11-01.
 */
@Data
@ToString
@Document(collection = "comment")
public class CommentVO {

	@Id
	private String _id;
	@Indexed
	private int postCd;
	private String userId;
	private String userName;
	private String ip;
	private String link;
	private String profile_image_url;
	private String regDt;
	private String comment;
	private List<ReplyVO> replys = new ArrayList<>();

	public CommentVO() {
		super();
		this.regDt = CustomStringUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm");
	}

	public void addReplys(ReplyVO replyVO) {
		if(this.replys == null) {
			this.replys = new ArrayList<>();
		}
		this.replys.add(replyVO);
	}

	public void setUserData(MemberVO memberVO) {
		if (memberVO != null) {
			this.userId = memberVO.getId();
			this.userName = memberVO.getUsername();
			this.link = memberVO.getLink();
			this.profile_image_url = memberVO.getProfile_image_url();
		}
	}
}
