package com.example.springDemo.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//@RestController
@Controller
public class HelloController {
   @Value("${name}")
   private String username;
   
   @Value("${bookTitle}")
   private String bookTitle;
   
   @Value("${bootname}")
   private String bootname;
   @RequestMapping("/map")
   public String index(ModelMap map) throws UnsupportedEncodingException {
	   
	   map.addAttribute("username", username);
	   //map.addAttribute("bookTitle", new String(bookTitle.getBytes("ISO-8859-1"),"UTF-8"));
	   map.addAttribute("bookTitle", bookTitle);
	   map.addAttribute("bootname", bootname);
	   return "welcome";
   }
}
