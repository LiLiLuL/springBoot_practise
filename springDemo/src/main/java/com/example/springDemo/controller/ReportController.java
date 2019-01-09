package com.example.springDemo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springDemo.RestfulTools;
import com.example.springDemo.ResultModel;
import com.example.springDemo.domain.Customer;
import com.example.springDemo.domain.Excel;
import com.example.springDemo.domain.ExcelReport;
import com.example.springDemo.domain.Person;
import com.example.springDemo.domain.Report;
import com.example.springDemo.domain.ReportRepository;
import com.example.springDemo.domain.SearchResult;
import com.example.springDemo.domain.SearchResultRepository;
import com.example.springDemo.service.ReportService;

import io.swagger.annotations.ApiOperation;
import com.avaje.ebean.Ebean;
//import com.avaje.ebean.*;
@RestController
@RequestMapping(value = "/report")
public class ReportController {
	@Autowired
	private ReportRepository reportRepository;
	@Autowired
	private SearchResultRepository searchRepository;
	@Autowired
	private ReportService reportService;
	private Excel excel;
	// 通过@ApiOperation注解来给API增加说明、通过@ApiImplicitParams、@ApiImplicitParam注解来给参数增加说明。
	static Map<String, Report> books = Collections.synchronizedMap(new HashMap<String, Report>());

	// 获得所有的报表数据内容
	@ApiOperation(value = "获取数据库中的所有报表内容", notes = "")
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Iterable<Report> getAllReport() {
		return reportRepository.findAll();
	}

	// 根据产品类型查找数据
	@ApiOperation(value = "根据产品类型获取数据库中的报表内容", notes = "")
	@RequestMapping(value = "/product/{productCategory}", method = RequestMethod.GET)
	public List<Report> getProductCategory(@PathVariable String productCategory) {
		System.out.println("111111111");
		return reportRepository.findProductCategory(productCategory);
	}

	// 根据基地组织查找数据 application/json;charset=UTF-8
	@ApiOperation(value = "根据基地组织获取数据库中的报表内容", notes = "")
	@RequestMapping(value = "/org/organization", method = { RequestMethod.POST, RequestMethod.GET }) // @RequestParam(value="organization")
																										// String
																										// organization
	public List<Report> getOrganization(Organization organization) {
		System.out.println(organization.getOrganization());
		System.out.println("222222222222");
		return reportRepository.findByOrganization(organization.getOrganization());
	}

	// 根据基地组织查找数据 application/json;charset=UTF-8
	@ApiOperation(value = "根据基地组织获取数据库中的报表内容", notes = "")
	@RequestMapping(value = "/organization", method = RequestMethod.POST) // @RequestParam(value="organization") String
																			// organization
	public List<Report> getOrg(@RequestBody Organization organization) {

		System.out.println(organization.getOrganization());
		System.out.println("222222222222");
		return reportRepository.findByOrganization(organization.getOrganization());
	}

	// 根据厂别查找数据
	@ApiOperation(value = "根据厂别获取数据库中的报表内容", notes = "")
	@RequestMapping(value = { "/factoryCategory" }, method = RequestMethod.GET)
	public ResultModel findByName(@RequestParam(value = "factoryCategory") String factoryCategory) {
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

	// 根据厂别查找数据
	@ApiOperation(value = "根据厂别获取数据库中的报表内容", notes = "")
	@RequestMapping(value = { "/factory" }, method = RequestMethod.POST)
	public ResultModel findByFactory(@RequestBody Report report) {
		try {
			String factoryCategory = report.getFactoryCategory();
			List<Report> userLs = reportRepository.findByFactoryCategory(factoryCategory);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("content", userLs);
			return RestfulTools.result(0, "", map);
		} catch (Exception e) {
			return RestfulTools.result(404, e.getMessage(), null);
		}
	}

	// 多条件查询，可以在当前条件下根据新的条件查询数据
	@ApiOperation(value = "根据多条件获取数据库中的报表内容，条件可以为空", notes = "")
	@RequestMapping(value = "/allsearch", method = RequestMethod.POST)
	public List<Report> findByValues(@RequestBody Report report) {

		String proCat = report.getProductCategory();
		String org = report.getOrganization();
		String facCat = report.getFactoryCategory();
		System.out.print(proCat + org + facCat);
		return reportService.findByAllsearch(proCat, org, facCat);

	}

	// 模糊查询
	@ApiOperation(value = "根据厂别获取数据库中的报表内容", notes = "")
	@RequestMapping(value = { "/search" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ResultModel findBySearch(@RequestParam(value = "name") String name) {
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

	// 根据开始时间和结束时间查找某一段时间内的数据
	@ApiOperation(value = "根据开始时间和结束时间获取数据库中的报表内容", notes = "")
	@RequestMapping(value = { "/date" }, method = { RequestMethod.GET, RequestMethod.POST })
	public List<Report> findByDate(@RequestBody Customer report) {
		Date startDate = report.getStartDate();
		Date enDate = report.getEnDate();
		System.out.print(startDate + "," + enDate);
		return reportRepository.findByDate(startDate, enDate);
	}

	// 更新原因分析
	@ApiOperation(value = "根据开始时间和结束时间获取数据库中的报表内容", notes = "")
	@RequestMapping(value = { "/updateReason" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ResultModel updateReason(@RequestBody Report report) {
		String reason = report.getReason();
		Integer id = report.getContentId();
		System.out.println(reason + id);
		try {
			reportRepository.updateByReason(reason, id);
			return RestfulTools.result(0, "success", null);
		} catch (Exception e) {
			// TODO: handle exception
			return RestfulTools.result(404, e.getMessage(), null);
		}

	}

	// 更新一条数据
	@ApiOperation(value = "根据用户上传的多条数据，首先判断数据库中是否含有此类数据，若有，则更新其内容，若没有则保存此内容", notes = "")
	@RequestMapping(value = { "/updateReport" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String updateTheReport(@RequestBody Report report) {
		String proCat = report.getProductCategory();
		String org = report.getOrganization();
		String facCat = report.getFactoryCategory();
		Date d = report.getNew_date();
		String productNumber = report.getProductNumber();
		Double monPlan = report.getMonplan();
		Double orgMonPlan = report.getOrgMonPlan();
		Double planPro = report.getPlanProduce();
		Double realPro = report.getRealProduce();
		String reason = report.getReason();
		if (reportService.isCopy(proCat, org, facCat, d)) {
			System.out.print("true");
			Integer id = reportService.isSame(proCat, org, facCat, d);
			reportRepository.updateTheSame(id, productNumber, monPlan, orgMonPlan, planPro, realPro, reason);
			return "update success";
		} else {
			System.out.print("存在性检查结果：" + "false");
			reportService.save(report);
			return "success";
		}
	}
	
	public void updateOneReport(Report report) {
		String proCat = report.getProductCategory();
		String org = report.getOrganization();
		String facCat = report.getFactoryCategory();
		Date d = report.getNew_date();
		String productNumber = report.getProductNumber();
		Double monPlan = report.getMonplan();
		Double orgMonPlan = report.getOrgMonPlan();
		Double planPro = report.getPlanProduce();
		Double realPro = report.getRealProduce();
		String reason = report.getReason();
		if (reportService.isCopy(proCat, org, facCat, d)) {
			System.out.print("true");
			Integer id = reportService.isSame(proCat, org, facCat, d);
			reportRepository.updateTheSame(id, productNumber, monPlan, orgMonPlan, planPro, realPro, reason);
		} else {
			System.out.print("存在性检查结果：" + "false");
			reportService.save(report);
		}
	}

	// 更新多条数据
	@ApiOperation(value = "根据用户上传的多条数据，首先判断数据库中是否含有此类数据，若有，则更新其内容，若没有则保存此内容", notes = "")
	@RequestMapping(value = { "/submit" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ResultModel submitReport(@RequestBody List<Report> report) {
		try {

			for(Report reportInfo: report) {
				System.out.println("reportInfo" + reportInfo);
				updateOneReport(reportInfo);
			}
//			Map<String, Object> map = new HashMap<String, Object>();
//			
//			map.put("content", report.toString());
//				输出map集合里的所有值
//				Set<String> set = map.keySet();
//		        for(String key:set){
//		            Object value = map.get(key);
//		            System.out.println("key:"+key+" value:"+value);
//		        }
//			for (Object value : map.values()) { 
//				  System.out.println( value); 
//				}
//		    Object array;
//		    array=map.get("content");
			return RestfulTools.result(0, "success", null);
		} catch (Exception e) {
			return RestfulTools.result(404, e.getMessage(), null);
		}
	}
	
	
	@ApiOperation(value = "将表格中的数据以excel的形式导出", notes = "")
	@RequestMapping(value = { "/downloadtest" }, method = { RequestMethod.GET, RequestMethod.POST })
	public void downloadtest(HttpServletResponse response) {
		//模拟从数据库获取需要导出的数据
        List<Person> personList = new ArrayList<>();
        Person person1 = new Person("路飞","1",new Date());
        Person person2 = new Person("娜美","2", DateUtils.addDays(new Date(),3));
        Person person3 = new Person("索隆","1", DateUtils.addDays(new Date(),10));
        Person person4 = new Person("小狸猫","1", DateUtils.addDays(new Date(),-10));
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);

        //导出操作
        excel.exportExcel(personList,"花名册","草帽一伙",Person.class,"海贼王.xls",response);


	}
	
	
	@ApiOperation(value = "将表格中的数据以excel的形式导出", notes = "")
	@RequestMapping(value = { "/download" }, method = { RequestMethod.GET, RequestMethod.POST })
	public void download(@RequestBody List<Report> test,HttpServletResponse response) {
		//模拟从数据库获取需要导出的数据	
		
		List <ExcelReport> reports=new ArrayList<>();
		for(Report reportInfo: test) {
			ExcelReport report=new ExcelReport();
			report.setAchievement(reportInfo.getAchievement());
			report.setFactoryCategory(reportInfo.getFactoryCategory());
			report.setProductCategory(reportInfo.getProductCategory());
			report.setOrganization(reportInfo.getOrganization());
			report.setGap(reportInfo.getGap());
			report.setMonplan(reportInfo.getMonplan());
			report.setOrgMonPlan(reportInfo.getOrgMonPlan());
			report.setPlanProduce(reportInfo.getPlanProduce());
			report.setRealProduce(reportInfo.getRealProduce());
			report.setNew_date(reportInfo.getNew_date());
			report.setProductNumber(reportInfo.getProductNumber());
			report.setReason(reportInfo.getReason());
		    reports.add(report);
		}
        //导出操作
		System.out.println(reports.size());
        excel.exportExcel(reports,"所有内容","GAP达成率",ExcelReport.class,"海贼王.xls",response);
	}
	@ApiOperation(value="以日、月、周的形式分别查找表格数据",notes="")
	@RequestMapping(value = { "/datedef" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ResultModel find(@RequestParam Integer num) {	
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(num==3) {
				searchRepository.findByDefrence();
				List<SearchResult> reports=searchRepository.findAll();
				map.clear();
				map.put("result", reports);
			}else if(num==2){
				System.out.println(num);
				//searchRepository.findByWeek();	
				//List<SearchResult> reports=searchRepository.findAllNew();
				//List<SearchResult> reports =Ebean.getServer("mysql").find(SearchResult.class).where().findList();
				
				
				List<SearchResult> reports=searchRepository.findAll();
				for(SearchResult reportInfo: reports) {
					System.out.println(reportInfo);
				}
				System.out.println(reports);
				map.clear();
				map.put("result", reports);
			}else {
				List<Report> reports=reportRepository.findAll();
				
				map.clear();
				map.put("result", reports);
				
			}		
			return RestfulTools.result(0, "", map);
		} catch (Exception e) {
			return RestfulTools.result(404, e.getMessage(), null);
		}
		
	}

}
