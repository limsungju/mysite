package kr.co.itcen.mysite.search;

import kr.co.itcen.web.PaginationUtil;

public class Search {
	private int page;
	private String kwd;
	private PaginationUtil pagination;
	private int strNo;
	private int endNo;

	public Search() {
		this.kwd = "";
		this.page = 1;
		this.pagination = null;
	}
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getKwd() {
		return kwd;
	}

	public void setKwd(String kwd) {
		this.kwd = kwd;
	}

	public PaginationUtil getPagination() {
		return pagination;
	}

	public void setPagination(PaginationUtil pagination) {
		this.pagination = pagination;
	}

	public int getStrNo() {
		return strNo;
	}

	public void setStrNo(int strNo) {
		this.strNo = strNo;
	}

	public int getEndNo() {
		return endNo;
	}

	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}
	
	

}
