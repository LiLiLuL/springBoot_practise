package com.example.springDemo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="content_temp")
public class SearchResult {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="date_content",nullable=true)
    private String date_content;
	
	@Column(name="product_category", nullable=true)
    private String productCategory;
	
	@Column(name="organization",nullable=true)
    private String organization;
	
	@Column(name="factory_category",nullable=true)
    private String factoryCategory;
	
	@Column(name="Produce",nullable=true)
    private Double Produce;
	
	@Column(name="count",nullable=true)
    private Integer count;
	
	@Column(name="product_number",nullable=true)
	private String productNumber;
	@Column(name="Achievement",nullable=true)
	private String  Achievement;
	@Column(name="reason",nullable=true)
	private String reason;

    public SearchResult() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "{" +"date_content:"+date_content+","+"productCategory:"+ productCategory+","+"organization:"+organization+","+
	              "factoryCategory:"+factoryCategory+","+"produce:"+Produce+","+"count"+count+"achievement"+Achievement+","+
				"productNumber:"+productNumber+","+"reason:"+reason+"}";
	}
	public String getDate_content() {
		return date_content;
	}
	public void setDate_content(String date_content) {
		this.date_content = date_content;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String product_category) {
		this.productCategory = product_category;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getFactoryCategory() {
		return factoryCategory;
	}
	public void setFactoryCategory(String factory_category) {
		this.factoryCategory = factory_category;
	}
	public Double getPlanProduce() {
		return Produce;
	}
	public void setPlanProduce(Double planProduce) {
		this.Produce = Produce;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getProduce() {
		return Produce;
	}

	public void setProduce(Double produce) {
		Produce = produce;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getAchievement() {
		return Achievement;
	}

	public void setAchievement(String achievement) {
		Achievement = achievement;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


    
}
