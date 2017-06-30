package com.walter.model;

/**
 * Created by yhwang131 on 2017-06-29.
 */
public interface LuceneIndexVO {
	String getSeq();
	String getTitle();
	String getContent();
	void setSeq(String seq);
	void setTitle(String title);
	void setContent(String content);
}
