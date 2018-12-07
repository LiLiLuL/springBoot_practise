package com.example.springDemo.service;

import java.util.List;
import com.example.springDemo.domain.Report;

public interface ReportService {
   List <Report> findByProductCategoryLike( String name);
   List<Report> findByAllsearch(String proCat,String org,String facCat);
}
