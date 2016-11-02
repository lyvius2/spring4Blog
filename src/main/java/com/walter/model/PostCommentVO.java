package com.walter.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yhwang131 on 2016-11-02.
 */
public class PostCommentVO {

	@Id
	private String id;

	@Field
	private int parentPostCd;

	@Field
	private List<CommentVO> comments;

	public PostCommentVO() {
		super();
	}

	public PostCommentVO(int parentPostCd, CommentVO commentVO) {
		this.parentPostCd = parentPostCd;
		List<CommentVO> list = new ArrayList<>();
		list.add(commentVO);
		this.comments = list;
	}

	public String getId() {
		return id;
	}

	public int getParentPostCd() {
		return parentPostCd;
	}

	public List<CommentVO> getComments() {
		return comments;
	}

	@Override
	public String toString() {
		return "PostCommentVO{" +
				"id='" + id + '\'' +
				", parentPostCd=" + parentPostCd +
				", comments=" + comments +
				'}';
	}
}
