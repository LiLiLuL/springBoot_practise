package com.example.springDemo.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;



@RestController
public class CustomerController {
	
	@RequestMapping(value="/sayHello",method=RequestMethod.POST,consumes="application/json")
     public String sayHello(@RequestParam String username) {
		String str="Hello,"+username;
		return str;
	}
	//这是注释
    @RequestMapping(value = "/users/{username}",method = RequestMethod.GET,consumes="application/json")
    public String getUser(@PathVariable String username, @RequestParam String pwd){
        return "Welcome,"+username;
   }
    @RequestMapping("/hello")
    public String index() {
   
        return "Hello World yo";
    }
   
    
    }


