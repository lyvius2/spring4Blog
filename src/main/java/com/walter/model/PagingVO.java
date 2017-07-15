package com.walter.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yhwang131 on 2016-10-31.
 */
@Data
public class PagingVO {

	private int rowsPerPage;
	private int firstPageNo;
	private int prevPageNo;
	private int startPageNo;
	private int currPageNo;
	private int endPageNo;
	private int nextPageNo;
	private int finalPageNo;
	private int numberOfRows;
	private int sizeOfPage;

	private List<Integer> pagingNumbers;

	public PagingVO(int currPageNo, int rowsPerPage) {
		this.currPageNo = currPageNo;
		this.sizeOfPage = 5;
		this.rowsPerPage = (rowsPerPage!=0)?rowsPerPage:5;
	}

	private void setPagingNumbers(int startPageNo, int endPageNo) {
		List<Integer> nums = new ArrayList<>();
		nums.add(startPageNo);
		int len = endPageNo - startPageNo + 1;
		for(int i = 1; i < len; i++) {
			nums.add(startPageNo + i);
		}
		this.pagingNumbers = nums;
	}

	public void Paging() {
		if(numberOfRows == 0) return;
		if(currPageNo == 0) this.setCurrPageNo(1);
		if(rowsPerPage == 0) this.setRowsPerPage(5);

		if(currPageNo < 0) this.setCurrPageNo(1);
		int finalPage = (numberOfRows + (rowsPerPage - 1)) / rowsPerPage;
		if(currPageNo > finalPage) this.setCurrPageNo(finalPage);

		boolean isNowFirst = (currPageNo == 1?true:false);
		boolean isNowFinal = (currPageNo == finalPage?true:false);

		int startPage = ((currPageNo - 1) / sizeOfPage) * sizeOfPage + 1;
		int endPage = startPage + sizeOfPage - 1;

		if(endPage > finalPage) endPage = finalPage;
		this.setFirstPageNo(1);

		if(isNowFirst) {
			this.setPrevPageNo(1);
		} else {
			this.setPrevPageNo(((currPageNo - 1) < 1?1:(currPageNo - 1)));
		}
		this.setStartPageNo(startPage);
		this.setEndPageNo(endPage);

		if(isNowFinal) {
			this.setNextPageNo(finalPage);
		} else {
			this.setNextPageNo(((currPageNo + 1) < finalPage?finalPage:(currPageNo + 1)));
		}

		this.setFinalPageNo(finalPage);

		setPagingNumbers(this.startPageNo, this.endPageNo);
	}
}
