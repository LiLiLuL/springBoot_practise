package com.example.springDemo.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springDemo.domain.Report;
import com.example.springDemo.domain.ReportRepository;
import com.example.springDemo.service.ReportService;
import com.example.springDemo.RestfulTools;
import com.example.springDemo.ResultModel;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value="/report")
public class ReportController {
	@Autowired
    private ReportRepository reportRepository;	
	@Autowired
	private ReportService reportService;
	//通过@ApiOperation注解来给API增加说明、通过@ApiImplicitParams、@ApiImplicitParam注解来给参数增加说明。
	static Map<String, Report> books = Collections.synchronizedMap(new HashMap<String, Report>());
	
	//获得所有的报表数据内容
	@ApiOperation(value="获取数据库中的所有报表内容",notes="")
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public Iterable <Report> getAllReport(){
		return reportRepository.findAll();
	}
	
	//根据产品类型查找数据
	@ApiOperation(value="根据产品类型获取数据库中的报表内容",notes="")
	@RequestMapping(value="/product/{productCategory}",method=RequestMethod.GET)
	public List<Report> getProductCategory(@PathVariable String productCategory) {
		System.out.println("111111111");
		return reportRepository.findProductCategory(productCategory);
	}
		
	//根据基地组织查找数据  application/json;charset=UTF-8
	@ApiOperation(value="根据基地组织获取数据库中的报表内容",notes="")
	@RequestMapping(value="/org/organization",method= {RequestMethod.POST,RequestMethod.GET})//@RequestParam(value="organization") String organization
	public List<Report> getOrganization(Organization organization) {
		System.out.println(organization.getOrganization());
		System.out.println("222222222222");
		return reportRepository.findByOrganization(organization.getOrganization());
	}
	
	//根据基地组织查找数据  application/json;charset=UTF-8
	@ApiOperation(value="根据基地组织获取数据库中的报表内容",notes="")
	@RequestMapping(value="/organization",method=RequestMethod.POST)//@RequestParam(value="organization") String organization
	public List<Report> getOrg(@RequestBody Organization organization) {
		
		System.out.println(organization.getOrganization());
		System.out.println("222222222222");
		return reportRepository.findByOrganization(organization.getOrganization());
	}
	
	//根据厂别查找数据
	@ApiOperation(value="根据厂别获取数据库中的报表内容",notes="")
	@RequestMapping(value = { "/factoryCategory" }, method = RequestMethod.GET)
	public  ResultModel findByName(@RequestParam(value="factoryCategory") String factoryCategory) {
		try {
		   System.out.print(factoryCategory);
			List<Report> userLs = reportRepository.findByFactoryCategory(factoryCategory);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("content", userLs);
			return RestfulTools.result(0, "", map);
		} catch (Exception e) {
			return RestfulTools.result(404, e.getMessage(), null);
		}
	}
	
	//根据厂别查找数据
		@ApiOperation(value="根据厂别获取数据库中的报表内容",notes="")
		@RequestMapping(value = { "/factory" }, method = RequestMethod.POST)
		public  ResultModel findByFactory(@RequestBody Report  report) {
			try {
			    String factoryCategory=report.getFactoryCategory();
				List<Report> userLs = reportRepository.findByFactoryCategory(factoryCategory);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("content", userLs);
				return RestfulTools.result(0, "", map);
			} catch (Exception e) {
				return RestfulTools.result(404, e.getMessage(), null);
			}
		}
    //	多条件查询，可以在当前条件下根据新的条件查询数据
	@ApiOperation(value="根据多条件获取数据库中的报表内容，条件可以为空",notes="")
	@RequestMapping(value="/allsearch",method=RequestMethod.POST)
	public List<Report> findByValues(@RequestBody Report report) {
		
		String proCat=report.getProductCategory();
		String org=report.getOrganization();
		String facCat=report.getFactoryCategory();
		 System.out.print(proCat+org+facCat);
	     return reportService.findByAllsearch(proCat, org, facCat);
			
		
	}

	//模糊查询
	@ApiOperation(value="根据厂别获取数据库中的报表内容",notes="")
	@RequestMapping(value = { "/search" }, method = {RequestMethod.GET,RequestMethod.POST})
	public ResultModel findBySearch(@RequestParam(value="name")String name) {
		try {
			   System.out.print(name);
				List<Report> userLs = reportRepository.findByProductCategoryLike(name);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("content", userLs);
				return RestfulTools.result(0, "", map);
			} catch (Exception e) {
				return RestfulTools.result(404, e.getMessage(), null);
			}
	}

	//根据开始时间和结束时间查找某一段时间内的数据
	@ApiOperation(value="根据开始时间和结束时间获取数据库中的报表内容",notes="")
	@RequestMapping(value = { "/date" }, method = {RequestMethod.GET,RequestMethod.POST})
	public List<Report> findByDate(@RequestBody Report report) {
	    Date startDate=report.getStartDate();
	    Date enDate=report.getEnDate();
	    System.out.print(startDate+","+enDate);
	    return reportRepository.findByDate(startDate, enDate);
	}
	
	//更新原因分析
	@ApiOperation(value="根据开始时间和结束时间获取数据库中的报表内容",notes="")
	@RequestMapping(value = { "/updateReason" }, method = {RequestMethod.GET,RequestMethod.POST})
	public  ResultModel updateReason(@RequestBody Report report){
		String reason=report.getReason();
		Long id=report.getContentId();
		System.out.println(reason+id);
	try {
		reportRepository.updateByReason(reason, id);
		return RestfulTools.result(0, "success", null);
	} catch (Exception e) {
		// TODO: handle exception
		 return RestfulTools.result(404, e.getMessage(), null);
	}
	   
	}
	
	
	
}
