package com.walter.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yhwang131 on 2016-11-01.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Document(collection = "comment")
public class CommentVO extends ReplyVO {

	@Id
	private String _id;
	@Indexed
	private int postCd;
	private List<ReplyVO> replys = new ArrayList<>();

	public CommentVO() {
		super();
	}

	public void addReplys(ReplyVO replyVO) {
		if(this.replys == null) {
			this.replys = new ArrayList<>();
		}
		this.replys.add(replyVO);
	}
}
