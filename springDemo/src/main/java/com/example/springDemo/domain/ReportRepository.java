package com.example.springDemo.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ReportRepository extends JpaRepository<Report, Double> {
	@Query("select r from Report r where r.organization=:organization")
	List<Report> findByOrganization(String organization);

	@Query("select r from Report r where r.productCategory=:productCategory")
	List<Report> findProductCategory(@Param("productCategory") String productCategory);

	List<Report> findByFactoryCategory(String factoryCategory);

	@Query("select  r from  Report r where r.productCategory like CONCAT('%',:name,'%') ")
	List<Report> findByProductCategoryLike(@Param("name") String name);

//    @Query(value="select * from content as r where r.product_category=?1  and r.organization=?2  and r.factory_category=?3 ",nativeQuery=true)
	@Query(value = "select * from content as r where if(?1 !='',r.product_category=?1,1=1)"
			+ "and if(?2 != '',r.organization=?2,1=1)"
			+ "and if(?3 !='',r.factory_category=?3,1=1)", nativeQuery = true)
	List<Report> findByAllsearch(String proCat, String org, String facCat);

	@Query(value = "select * from content as r where r.gapa_date>?1 and r.gapa_date<?2", nativeQuery = true)
	List<Report> findByDate(Date startDate, Date enDate);

	// CASE WHEN ?1 IS NULL THEN r.reason ELSE ?1 END
	@Transactional
	@Modifying
	@Query(value = "update content as r set " + "r.reason=CASE WHEN ?1 IS NULL THEN r.reason ELSE ?1 END "
			+ "where r.gapa_id=?2", nativeQuery = true)
	void updateByReason(String reason, Integer id);

//	@Query("")
//	List <Report> saveData(Report r);

	// 判断传来的数据是否在数据库中存在
	@Query(value = "select * from content as r where if(?1 !='',r.product_category=?1,1=1)"
			+ "and if(?2 != '',r.organization=?2,1=1)" + "and if(?3 !='',r.factory_category=?3,1=1)"
			+ "and if(?4 !='',r.gapa_date=?4,1=1)", nativeQuery = true)
	List<Report> isCopy(String proCat, String org, String facCat, Date d);

	// 更新在数据库中已有的数据的某些字段
	@Transactional
	@Modifying
	@Query(value = "update content as r set "
			+ "r.product_number=CASE WHEN ?2 IS NULL THEN r.product_number ELSE ?2 END ,"
			+ "r.month_plan=CASE WHEN ?3 IS NULL THEN r.month_plan ELSE ?3 END,"
			+ "r.organization_month_plan=CASE WHEN ?4 IS NULL THEN r.organization_month_plan ELSE ?4 END,"
			+ "r.plan_produce=CASE WHEN ?5 IS NULL THEN r.plan_produce ELSE ?5 END,"
			+ "r.real_produce=CASE WHEN ?6 IS NULL THEN r.real_produce ELSE ?6 END,"
			+ "r.reason=CASE WHEN ?7 IS NULL THEN r.reason ELSE ?7 END " + "where r.gapa_id=?1", nativeQuery = true)
	void updateTheSame(Integer id, String productNumber, Double monplan, Double orgMonPlan, Double planProduce,
			Double realProduce, String reason);
	
	@Query(value="select gapa_id, DATE_FORMAT(gapa_date,'%Y%M') date_content,product_category,organization,"
			+ "factory_category,sum(plan_produce) planProduce, count(gapa_id) count "
			+ "from content as r group by date_content,product_category,organization,factory_category",nativeQuery = true)	
	List<Report> findByDefrence();
}
