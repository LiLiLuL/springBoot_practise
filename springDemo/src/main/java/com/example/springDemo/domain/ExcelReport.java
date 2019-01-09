package com.example.springDemo.domain;

import java.util.Date;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class ExcelReport {
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Excel(name="产品ID",orderNum="1",isImportField="id")
	private Integer contentId; // 产品id

	@Excel(name="产品类型",orderNum="2",isImportField="productCategory")
	private String productCategory; // 产品类型
	
	@Excel(name="基地组织",orderNum="3",isImportField="organization")
	private String organization; // 基地组织

	@Excel(name="厂别",orderNum="4",isImportField="factoryCategory")
	private String factoryCategory; // 厂别

	@Excel(name="产品型号",orderNum="5",isImportField="productNumber")
	private String productNumber; // 产品型号

	@Excel(name="M+1锁定规划",orderNum="6",isImportField="monplan")
	private Double monplan; // M+1锁定规划

	@Excel(name="基地月锁定规划",orderNum="7",isImportField="orgMonPlan")
	private Double orgMonPlan; // 基地月锁定规划

	@Excel(name="量产计划产出",orderNum="8",isImportField="planProduce")
	private Double planProduce; // 量产计划产出

	@Excel(name="量产实际产出",orderNum="9",isImportField="realProduce")
	private Double realProduce; // 量产实际产出

	@Excel(name="gap值",orderNum="10",isImportField="gap")
	private Double gap; // gap值

	@Excel(name="达成率",orderNum="11",isImportField="achievement")
	private Double achievement; // 达成率
	
	@Excel(name="原因分析",orderNum="12",isImportField="reason")
	private String reason; // 原因分析
	
	@Excel(name="时间",orderNum="13",exportFormat = "yyyy-MM-dd",isImportField="new_date")
	private Date new_date;
	public ExcelReport() {
		
	}
	public ExcelReport(Integer contentId,String productCategory, String organization, 
			String factoryCategory,String productNumber,Double monplan,Double orgMonPlan,
			Double planProduce,Double realProduce,Double gap,Double achievement,String reason,Date new_date) {
		  this.contentId=contentId;
		  this.productCategory=productCategory;
		  this.organization=organization;
		  this.factoryCategory=factoryCategory;
		  this.productNumber=productNumber;
		  this.monplan=monplan;
		  this.orgMonPlan=orgMonPlan;
		  this.planProduce=planProduce;
		  this.realProduce=this.realProduce;
		  this.gap=gap;
		  this.achievement=achievement;
		  this.new_date=new_date;
		
	}

	public Integer getContentId() {
		return contentId;
	}

	public void setContentId(Integer contentId) {
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
	

}
