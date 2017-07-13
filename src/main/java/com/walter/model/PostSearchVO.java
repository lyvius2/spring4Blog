package com.walter.model;

import lombok.Data;

/**
 * Created by yhwang131 on 2016-10-31.
 */
@Data
public class PostSearchVO {

	private String searchText;
	private int category_cd;
	private Boolean use_yn;
	private int currPageNo;
	private int offset;
	private int rowsPerPage;

	public PostSearchVO() {
		super();
		if(this.getCurrPageNo() == 0) this.setCurrPageNo(1);
	}
}
