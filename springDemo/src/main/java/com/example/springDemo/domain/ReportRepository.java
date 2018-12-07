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
	@Query(value = "update content as r set " + "r.reason=CASE WHEN ?1 IS NULL THEN r.reason ELSE ?1 END " + "where r.gapa_id=?2", nativeQuery = true)
	void  updateByReason(String reason, Long id);
}
