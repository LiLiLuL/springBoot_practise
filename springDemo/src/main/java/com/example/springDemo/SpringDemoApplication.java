//1、Application.java 建议放到跟目录下面,主要用于做一些框架配置
//
//2、domain目录主要用于实体（Entity）与数据访问层（Repository）
//
//3、service 层主要是业务类代码
//
//4、controller 负责页面访问控制

package com.example.springDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication; 

@SpringBootApplication
public class SpringDemoApplication {

	public static void main(String[] args) {
	   SpringApplication.run(SpringDemoApplication.class, args);
	}
	
}
