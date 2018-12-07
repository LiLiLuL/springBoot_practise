package com.example.springDemo.controller;

public class Organization {
	private String organization;

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	@Override
	public String toString() {
		return "Organization [organization=" + organization + "]";
	}
	
}
