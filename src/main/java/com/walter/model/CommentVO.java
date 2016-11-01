package com.walter.model;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

/**
 * Created by yhwang131 on 2016-11-01.
 */
public class CommentVO {
	@Id
	private String id;

	private int parent_post_id;
	private List<Comment> comments;

	public String getId() {
		return id;
	}

	public int getParent_post_id() {
		return parent_post_id;
	}

	public List<Comment> getComments() {
		return comments;
	}

	@Override
	public String toString() {
		return "CommentVO{" +
				"id='" + id + '\'' +
				", parent_post_id=" + parent_post_id +
				", comments=" + comments +
				'}';
	}

	class Comment {
		private String user;
		private String ip;
		private Date reg_dt;
		private String comment;
		private List<Comment> replys;

		public String getUser() {
			return user;
		}

		public String getIp() {
			return ip;
		}

		public Date getReg_dt() {
			return reg_dt;
		}

		public String getComment() {
			return comment;
		}

		public List<Comment> getReplys() {
			return replys;
		}

		@Override
		public String toString() {
			return "Comment{" +
					"user='" + user + '\'' +
					", ip='" + ip + '\'' +
					", reg_dt=" + reg_dt +
					", comment='" + comment + '\'' +
					", replys=" + replys +
					'}';
		}
	}
}
