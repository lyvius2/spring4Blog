package com.walter.model;

/**
 * Created by yhwang131 on 2016-10-31.
 */
public class PostSearchVO {

	private String searchText;
	private int category_cd;
	private int currPageNo;
	private int offset;
	private int rowsPerPage;

	public PostSearchVO() {
		super();
		if(this.getCurrPageNo() == 0) this.setCurrPageNo(1);
		if(this.getRowsPerPage() == 0) this.setRowsPerPage(5);
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public int getCategory_cd() {
		return category_cd;
	}

	public void setCategory_cd(int category_cd) {
		this.category_cd = category_cd;
	}

	public int getCurrPageNo() {
		return currPageNo;
	}

	public void setCurrPageNo(int currPageNo) {
		this.currPageNo = currPageNo;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	@Override
	public String toString() {
		return "PostSearchVO{" +
				"searchText='" + searchText + '\'' +
				", category_cd=" + category_cd +
				", currPageNo=" + currPageNo +
				", offset=" + offset +
				", rowsPerPage=" + rowsPerPage +
				'}';
	}
}
