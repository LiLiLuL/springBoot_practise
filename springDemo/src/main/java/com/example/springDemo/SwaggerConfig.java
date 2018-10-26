package com.example.springDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//这两个注解，一个是swagger2的配置，一个是项目启动的时候启动swagger2
@Configuration
@EnableSwagger2 
public class SwaggerConfig {
	//swagger2的配置文件，配置swagger2的一些基本内容
   @Bean
   public Docket createRestApi() {
	   
	   return new Docket(DocumentationType.SWAGGER_2)
			   .apiInfo(apiInfo())
			   .select()
			   .apis(RequestHandlerSelectors.basePackage("com.example.springDemo"))
			   .paths(PathSelectors.any())
			   .build();
	   
   }
   //构建api文档的详细信息函数
   private ApiInfo apiInfo() {
	   return new ApiInfoBuilder()
			   // 页面标题
			           .title("使用swagger2构建restful apis")
			           .termsOfServiceUrl("http://localhost:3333")
			           //创建人
			           .contact(new Contact("chenxq","http://www.baidu.com",""))
			           //版本号
			           .version("1.0.0")
			           //描述
			           .description("客户端与服务端的接口文档")
			           .build();
   }
}
