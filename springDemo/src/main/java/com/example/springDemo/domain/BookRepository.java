package com.example.springDemo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book,Long>{
             Book findByTitle(String title);
             Book findByTitleAndAuthor(String title,String author);
             @Query("from Book b where b.title=:title")
             Book findBook(@Param("title")String bookTitle);
             @Query("select book from Book book where book.author = :author")
             Page findBookPage(Pageable pageable, @Param("author") String author);

}
