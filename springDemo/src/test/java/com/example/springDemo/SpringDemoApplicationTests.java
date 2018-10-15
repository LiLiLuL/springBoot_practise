package com.example.springDemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDemoApplicationTests {

	@Value("${com.example.springDemo.author}")
	private String myAuthor;
	
	@Value("${com.example.springDemo.title}")
	private String myTitle;
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Test
	public void Test1(){
		try {
			 Assert.assertEquals("bluecoffee", myAuthor);
	         Assert.assertEquals("Spring Boot基础教程", myTitle);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
