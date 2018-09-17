package com.fqt.project.entity;

public class Page {
	
	private Integer currentPage;
	private Integer totalPage;
	private Integer perItem;
	private Integer totalItem;
	
	public Page(Integer currentPage, Integer perItem, Integer totalItem) {
		super();
		this.currentPage = currentPage;
		this.perItem = perItem;
		this.totalItem = totalItem;
	}
	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getTotalPage() {
		return totalItem%perItem==0?(totalItem/perItem):(totalItem/perItem+1);
	}
	public Integer getPerItem() {
		return perItem;
	}
	public void setPerItem(Integer perItem) {
		this.perItem = perItem;
	}
	public Integer getTotalItem() {
		return totalItem;
	}
	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}

}
