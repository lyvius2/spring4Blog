package com.walter.util;

/**
 * Created by yhwang131 on 2017-06-19.
 */
public enum Message {
	DUPLE_CATEGORY("같은 이름으로 이미 등록된 카테고리가 있습니다."),
	ERROR_SOCIAL_API("소셜 로그인에 실패했습니다. 정보를 가져오지 못했습니다.");

	final private String text;

	Message(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
