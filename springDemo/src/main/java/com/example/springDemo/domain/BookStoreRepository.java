package com.example.springDemo.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BookStoreRepository extends JpaRepository<BookStore,Long>{
	 @Query("from BookStore bs where bs.storeManager = :storeManager")
	   List<BookStore> getBookStoreByManager(@Param("storeManager") String storeManager);
}
