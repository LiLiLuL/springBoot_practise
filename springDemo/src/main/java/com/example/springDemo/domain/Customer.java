package com.example.springDemo.domain;

import java.util.Date;

public class Customer {
	private Date startDate;
	private Date enDate;
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEnDate() {
		return enDate;
	}
	public void setEnDate(Date enDate) {
		this.enDate = enDate;
	}
}
