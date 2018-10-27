package com.hong.bean;

import java.util.List;

public class Order {

	
	private Integer id;
	
	private Integer tableId;
	
	private Integer total;
	
	private String status;
	
	private String remark;
	
	private String createtime;
	
	private TableR tableR;
	
	private List<Orderdetail> orderdetails;
	
	public List<Orderdetail> getOrderdetails() {
		return orderdetails;
	}


	public void setOrderdetails(List<Orderdetail> orderdetails) {
		this.orderdetails = orderdetails;
	}


	public TableR getTableR() {
		return tableR;
	}


	public void setTableR(TableR tableR) {
		this.tableR = tableR;
	}


	public Integer getId() {
		return id;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	
	public Integer getTableId() {
		return tableId;
	}

	
	public void setTableId(Integer tableId) {
		this.tableId = tableId;
	}

	
	public Integer getTotal() {
		return total;
	}

	
	public void setTotal(Integer total) {
		this.total = total;
	}

	
	public String getStatus() {
		return status;
	}

	
	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getRemark() {
		return remark;
	}

	
	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	public String getCreatetime() {
		return createtime;
	}

	
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
}