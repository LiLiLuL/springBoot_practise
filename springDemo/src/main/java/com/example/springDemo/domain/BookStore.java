package com.example.springDemo.domain;

import javax.persistence.*;

@Entity
@Table(name="book_store")
public class BookStore {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE,generator="pk_gen")
    @TableGenerator(name = "pk_gen",
    table="book_generator",
    pkColumnName="book_name",
    valueColumnName="book_value",
    pkColumnValue="book_store_pk",
    allocationSize=1)
    private Long bookId;
    @Column(nullable = true)
    private String address;

    @Column(nullable = true)
    private String storeManager;
    
    public BookStore(){};
    public BookStore(String address,String storeManager){
        this.address = address;
        this.storeManager = storeManager;
    }

    public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStoreManager() {
		return storeManager;
	}

	public void setStoreManager(String storeManager) {
		this.storeManager = storeManager;
	}

}
