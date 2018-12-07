//1、Application.java 建议放到跟目录下面,主要用于做一些框架配置
//
//2、domain目录主要用于实体（Entity）与数据访问层（Repository）
//
//3、service 层主要是业务类代码
//
//4、controller 负责页面访问控制

package com.example.springDemo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.Include;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.ObjectMapper; 

@SpringBootApplication
public class SpringDemoApplication {

	public static void main(String[] args) {
	   SpringApplication.run(SpringDemoApplication.class, args);
	}
	
	@Bean
    public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
    	MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
    	//设置日期格式
    	ObjectMapper objectMapper = new ObjectMapper();
    	SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd");
    	objectMapper.setDateFormat(smt);
    	//去掉null
		//objectMapper.setSerializationInclusion(Include.NON_NULL);
    	mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
    	//设置中文编码格式
    	List<MediaType> list = new ArrayList<MediaType>();
    	list.add(MediaType.APPLICATION_JSON_UTF8);
    	mappingJackson2HttpMessageConverter.setSupportedMediaTypes(list);
    	return mappingJackson2HttpMessageConverter;
    }
}
