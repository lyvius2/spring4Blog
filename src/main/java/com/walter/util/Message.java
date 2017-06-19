package com.walter.util;

/**
 * Created by yhwang131 on 2017-06-19.
 */
public enum Message {
	DUPLE_CATEGORY("같은 이름으로 이미 등록된 카테고리가 있습니다.");

	private String comment;

	Message(String comment) {
		this.comment = comment;
	}

	public String getComment() {
		return comment;
	}
}
