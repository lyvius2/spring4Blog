package com.walter.model;

/**
 * Created by yhwang131 on 2016-10-31.
 */
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

	public PagingVO(int currPageNo, int rowsPerPage) {
		this.currPageNo = currPageNo;
		this.sizeOfPage = 5;
		this.rowsPerPage = (rowsPerPage!=0)?rowsPerPage:5;
	}

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public int getFirstPageNo() {
		return firstPageNo;
	}

	public void setFirstPageNo(int firstPageNo) {
		this.firstPageNo = firstPageNo;
	}

	public int getPrevPageNo() {
		return prevPageNo;
	}

	public void setPrevPageNo(int prevPageNo) {
		this.prevPageNo = prevPageNo;
	}

	public int getStartPageNo() {
		return startPageNo;
	}

	public void setStartPageNo(int startPageNo) {
		this.startPageNo = startPageNo;
	}

	public int getCurrPageNo() {
		return currPageNo;
	}

	public void setCurrPageNo(int currPageNo) {
		this.currPageNo = currPageNo;
	}

	public int getEndPageNo() {
		return endPageNo;
	}

	public void setEndPageNo(int endPageNo) {
		this.endPageNo = endPageNo;
	}

	public int getNextPageNo() {
		return nextPageNo;
	}

	public void setNextPageNo(int nextPageNo) {
		this.nextPageNo = nextPageNo;
	}

	public int getFinalPageNo() {
		return finalPageNo;
	}

	public void setFinalPageNo(int finalPageNo) {
		this.finalPageNo = finalPageNo;
	}

	public int getNumberOfRows() {
		return numberOfRows;
	}

	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

	public int getSizeOfPage() {
		return sizeOfPage;
	}

	public void setSizeOfPage(int sizeOfPage) {
		this.sizeOfPage = sizeOfPage;
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
	}
}
