package com.example.springDemo.domain;

import java.util.List;
import com.example.springDemo.domain.Report;
public class FormData {
	private List<Report> reports;

	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}
   
	@Override
    public String toString() {
        return "FormData [reports=" + reports + "]";
    }
}
