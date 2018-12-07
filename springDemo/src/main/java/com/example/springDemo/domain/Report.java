package com.example.springDemo.domain;


import java.util.Date;

import javax.persistence.*;



@Entity
@Table(name="content")
public class Report {
    @Id
    @Column(name="gapa_id",  nullable=false) 
    private Long contentId;  //产品id
    
    @Column(name="product_category",  nullable=false)
    private String productCategory;  //产品类型
    
    @Column(name="organization",nullable=false)
    private String organization;   //基地组织
    
    @Column(name="factory_category",nullable=false)
    private String factoryCategory;   //厂别
    
    @Column(name="product_number",nullable=true)
    private String productNumber;   //产品型号
    
    @Column(name="month_plan",nullable=false)
    private Double monplan;   //M+1锁定规划
    
    @Column(name="organization_month_plan",nullable=false)
    private Double orgMonPlan;   //基地月锁定规划   
    
    @Column(name="plan_produce",nullable=true)
    private Double planProduce;   //量产计划产出
    
    @Column(name="real_produce",nullable=true)
    private Double realProduce;   //量产实际产出
    
    @Column(name="gap",nullable=true)
    private Double gap;   //gap值
    
    @Column(name="achievement",nullable=true)
    private Double achievement;   //达成率
    
    @Column(name="reason",nullable=true)
    private String reason;   //原因分析
    
    @Column(name="gapa_date",nullable=true)
    private Date new_date;
     
    private Date startDate;
    private Date enDate;
    

	public Report() {}
    
    public Report(String productCategory,String organization, String productNumber ) {
    	this.productCategory=productCategory;
    	this.productNumber=productNumber;
    	this.organization=organization;
    }

	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
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

	public void setFactoryCategory(String factoryCategory) {
		this.factoryCategory = factoryCategory;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public Double getMonplan() {
		return monplan;
	}

	public void setMonplan(Double monplan) {
		this.monplan = monplan;
	}

	public Double getOrgMonPlan() {
		return orgMonPlan;
	}

	public void setOrgMonPlan(Double orgMonPlan) {
		this.orgMonPlan = orgMonPlan;
	}

	public Double getPlanProduce() {
		return planProduce;
	}

	public void setPlanProduce(Double planProduce) {
		this.planProduce = planProduce;
	}

	public Double getRealProduce() {
		return realProduce;
	}

	public void setRealProduce(Double realProduce) {
		this.realProduce = realProduce;
	}

	public Double getGap() {
		return gap;
	}

	public void setGap(Double gap) {
		this.gap = gap;
	}

	public Double getAchievement() {
		return achievement;
	}

	public void setAchievement(Double achievement) {
		this.achievement = achievement;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getNew_date() {
		return new_date;
	}

	public void setNew_date(Date new_date) {
		this.new_date = new_date;
	}

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
