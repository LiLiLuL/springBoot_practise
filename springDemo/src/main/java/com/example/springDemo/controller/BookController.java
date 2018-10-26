package com.example.springDemo.controller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.springDemo.domain.Book;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import com.example.springDemo.domain.BookRepository;



@RestController
@RequestMapping(value="/books")
public class BookController {
	
	@Autowired
    private BookRepository bookRepository;	
	//通过@ApiOperation注解来给API增加说明、通过@ApiImplicitParams、@ApiImplicitParam注解来给参数增加说明。
	static Map<String, Book> books = Collections.synchronizedMap(new HashMap<String, Book>());
	@ApiOperation(value="获取书籍列表",notes="")
	@RequestMapping(value="/",method=RequestMethod.GET)
	public List<Book> getUserList(){
/**
 * 		  处理"/books/"的GET请求，用来获取图书列表
            还可以通过@RequestParam传递参数来进行查询条件或者翻页信息的传递
		这里引入的List包一定是util里的，不能引错，List 是父类，
		当我们不用arraylist 而改用LinkedList的时候，前面的代码不需要改，只需要把后面的ArrayList改成LinkedList。
		同时ArrayList和LinkedList都继承List,但是又有扩展，里面的方法有些不一样，所以用父类List来new对象，
		new出来的对象只能使用父类中有的方法，不会造成以后改代码时的混乱
 */
       List<Book> result=new ArrayList<Book>(books.values());
		return result;
	}
	
	

	@ApiOperation(value="获取数据库中的所有书籍列表",notes="")
	@RequestMapping(value="/all",method=RequestMethod.GET)
    public Iterable<Book> getAllUsers() {
        return bookRepository.findAll();
    }
	
	 
	@ApiOperation(value="获取书籍详细信息",notes="根据url中的title来获取书籍详细信息")
	@ApiImplicitParam(name="title",value="书籍title",required=true,dataType="String")
    @RequestMapping(value="/{title}",method=RequestMethod.GET)
	public Book getBook(@PathVariable String title) {
			/**
			 * @PathVariable绑定URI模板变量值
	           @PathVariable是用来获得请求url中的动态参数的
	           @PathVariable用于将请求URL中的模板变量映射到功能处理方法的参数上
			 */
			// 处理"/books/{id}"的GET请求，用来获取url中id值的Book信息
	        // url中的id可通过@PathVariable绑定到函数的参数中
	        //return books.get(bookId);
			return bookRepository.findBook(title);
	}	
	
	
	
	 @ApiOperation(value="创建书籍",notes="根据Book对象创建书籍")
	 @ApiImplicitParam(name="book",value="书籍实体对象Book",required=true,dataType="Book")
	@PostMapping(value="/create",produces="application/json")
	public String createBook() {
		// 处理"/books/"的POST请求，用来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
		//@RequestBody该注解修饰的函数，会将结果直接填充到HTTP的响应体中一般配合@RequestMapping使用
        //books.put(book.getTitle(), book);
		 System.out.println("-------------------");
        bookRepository.save(new Book("book00001","xxxx",new Date()));
        return "success";
	}
	 
	
	 @ApiOperation(value="更新书籍信息",notes="根据url中的bookTitle来指定更新书籍，并根据传过来的Book信息来更新书籍详细信息")
	 @ApiImplicitParams({
		 @ApiImplicitParam(name="bookTitle",value="书籍Title",required=true,dataType="String"),
		 @ApiImplicitParam(name="book",value="书籍详细实体book",required=true,dataType="Book")
	 })	
	@RequestMapping(value="/update/{booktitle}",method=RequestMethod.PUT)
   public String putBook(@PathVariable String booktitle) {
		 // 处理"/books/{bookId}"的PUT请求，用来更新Book信息
		//Book b=books.getTitle(booktitle)
		// books.put(booktitle, b);
	    Book b=bookRepository.findBook(booktitle);
		b.setTitle("哎哟喂");
		b.setAuthor("akjdk");
		b.setNew_time(new Date());
		bookRepository.save(b);
		return "success";
	}	 
	@ApiOperation(value="删除书籍详细信息",notes="根据url中的bookId来获取书籍详细信息")
	@ApiImplicitParam(name="booktitle",value="书籍ID",required=true,dataType="String")
	@RequestMapping(value="/delete/{bookTitle}",method=RequestMethod.DELETE)
	public String deleteBook(@PathVariable String bookTitle) {
		 // 处理"/books/{bookId}"的DELETE请求，用来删除Book
		bookRepository.delete(bookRepository.findBook(bookTitle));
        return "success";		
	}
    }


