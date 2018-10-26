package com.example.springDemo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="book_book")
public class Book {
	@Id
    @GeneratedValue  //@GeneratedValue表示bookId我们设置为主键从1开始自增，但是通常我们在实际业务开发过程中，主键需要从自定义序列中获取
     private Long bookId;
	
	 @Column(nullable = false)
     private String title;
	 
	 @Column(nullable = true)
     private String author;
	 
	 @Column(name="new_time",nullable=true)
	 private Date new_time;
	 
     public Book(){}
     public Book(String title,String author,Date time) {
    	 this.title=title;
    	 this.author=author;
    	 this.new_time=time;
    	 
     }
     public Date getNew_time() {
		return new_time;
	}
	public void setNew_time(Date new_time) {
		this.new_time = new_time;
	}
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
//	private String time;
//     
//	public String getTime() {
//		return time;
//	}
//	public void setTime(String time) {
//		this.time = time;
//	}
     
}
