package com.walter.config.code;

/**
 * Created by yhwang131 on 2017-06-19.
 */
public enum Message {
	DUPLE_CATEGORY("같은 이름으로 이미 등록된 카테고리가 있습니다."),
	ERROR_SOCIAL_API("소셜 로그인에 실패했습니다. 정보를 가져오지 못했습니다."),
	ERROR_COMMENT_NOT_EXIST("댓글 내용을 입력하세요."),
	ERROR_REMOVE_HAVE_REPLY("대댓글이 있는 댓글은 관리자만이 삭제할 수 있습니다."),
	ERROR_REMOVE_PERMISSION("작성자 본인만이 삭제할 수 있습니다. ^^;"),
	ERROR_REMOVE_NOT_EXIST("삭제 대상이 존재하지 않습니다.");

	final private String text;

	Message(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
