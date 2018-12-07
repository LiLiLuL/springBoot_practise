package com.example.springDemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springDemo.domain.Report;
import com.example.springDemo.domain.ReportRepository;
@Service
public class ReportServiceImpl implements ReportService {
	  @Autowired
	   private ReportRepository report;
        @Override
        public List<Report> findByAllsearch(String proCat,String org,String facCat){
        	return report.findByAllsearch(proCat, org, facCat);
        }
		@Override
		public List<Report> findByProductCategoryLike(String name) {
			// TODO Auto-generated method stub
			return report.findByProductCategoryLike(name);
		}
}
