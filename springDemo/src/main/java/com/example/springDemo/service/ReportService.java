package com.example.springDemo.service;

import java.util.Date;
import java.util.List;
import com.example.springDemo.domain.Report;

public interface ReportService {
   List <Report> findByProductCategoryLike( String name);
   List<Report> findByAllsearch(String proCat,String org,String facCat);
   void save(Report report);
   boolean isCopy(String proCat, String org, String facCat,Date d);
   Integer isSame(String proCat, String org, String facCat,Date d);
}
