package com.example.springDemo.Filter;

import javax.servlet.Filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CorsFilter implements Filter {
	 @Override
	    public void init(FilterConfig filterConfig) throws ServletException {
	 
	    }
	 
	    @Override
	    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
	        HttpServletResponse response = (HttpServletResponse) servletResponse;
	        HttpServletRequest request = (HttpServletRequest)servletRequest;
	        System.out.println(request.getCharacterEncoding()); 	  
	        request.setCharacterEncoding("UTF-8");
	        String origin = request.getHeader("Origin");
	        response.setHeader("Access-Control-Allow-Origin", origin);
	        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
	        response.setHeader("Access-Control-Max-Age", "3600");
	        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Authorization");
	        response.setHeader("Access-Control-Allow-Credentials", "true");
	        response.setHeader("Access-Control-Allow-Headers", "Content-Type,XFILENAME,XFILECATEGORY,XFILESIZE");
	        String method = request.getMethod();
	        System.out.println("*********************************过滤器被使用**************************");  
	        if(method.equalsIgnoreCase("OPTIONS")){
	            servletResponse.getOutputStream().write("Success".getBytes("utf-8"));
//	          response.setStatus(HttpStatus.OK);
//	            
//				// hresp.setContentLength(0);
//	 
//				response.getWriter().write("OPTIONS returns OK");

	        }else{
	            filterChain.doFilter(servletRequest, servletResponse);
	        }
	    }
	 
	    @Override
	    public void destroy() {
	 
	    }
   
}
