package com.hong.bean;

import java.util.List;

/**
 * @Description 分页 bean
 * @author hong
 * @version 1.0
 * 
 *
 */
public class PageBean<T> {
	private  int currentPage; //当前页
	private int pageSize;	//页面记录数量
	private int totalPage;	//总页数
	private int totalRecord;	//总记录
	private List<T> beanList;	//当前记录
	
	public PageBean(int currentPage,int pageSize,int totalRecord){
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalRecord = totalRecord;
		this.setTotalPage(totalRecord);
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public void setTotalPage(int totalRecord) {
		this.totalPage = (totalRecord%pageSize)==0?(totalRecord/pageSize):(totalRecord/pageSize)+1;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
	
	
}
