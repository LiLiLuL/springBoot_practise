package com.example.springDemo.domain;

import java.util.List;

import org.apache.coyote.http11.filters.VoidInputFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SearchResultRepository  extends JpaRepository<SearchResult, Double> {
	//以月的形式展现数据
	@Transactional
	@Modifying
	@Query(value="CREATE OR REPLACE VIEW  content_temp as "
			+ "select  DATE_FORMAT(gapa_date,'%Y%M') date_content,product_category,organization,product_number,"
			+ "factory_category,sum(coalesce(plan_produce,0)) Produce,"
			+ "concat(TRUNCATE ((sum(coalesce(plan_produce,0))/sum(month_plan))*100,2),'%') Achievement,reason, count(gapa_id) count "
			+ "from content as r group by date_content,product_category,organization,factory_category",nativeQuery = true)
	void findByDefrence();

	
	//以周的形式显示数据
	@Transactional
	@Modifying
	@Query(value="CREATE OR REPLACE VIEW  content_temp as "
			+ "select  DATE_FORMAT(gapa_date,'%Y%M%u') date_content,product_category,organization,product_number,"
			+ "factory_category,sum(coalesce(plan_produce,0)) Produce,"
			+ "concat(TRUNCATE ((sum(coalesce(plan_produce,0))/sum(month_plan))*100,2),'%') Achievement,reason, count(gapa_id) count "
			+ "from content as r group by date_content,product_category,organization,factory_category",nativeQuery = true)
	void findByWeek();
	
	@Query(value="select @rowNum\\:=@rowNum+1 AS id,con.* from content_temp con,(select (@rowNum\\:=0)) r",nativeQuery=true)
	List<SearchResult> findAllNew();
	
}
