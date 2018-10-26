import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.springDemo.SpringDemoApplication;
import com.example.springDemo.domain.Book;
import com.example.springDemo.domain.BookRepository;
import com.example.springDemo.domain.BookStore;
import com.example.springDemo.domain.BookStoreRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringDemoApplication.class)
public class BookRepositoryTest {
	

	    @Autowired
	    private BookRepository bookRepositoy;

	    @Autowired
	    private BookStoreRepository bookStoreRepository;

	    @Test
	    public void testBook(){
	        try{
	            //先清空已有数据
	        	bookRepositoy.deleteAll();

	            //生成10本书
	            for(int i=1;i<=10;i++){
	            	bookRepositoy.save(new Book("book"+i,"author"+i,new Date()));
	            }

	            // 测试findAll, 查询所有记录
	            Assert.assertEquals(10, bookRepositoy.findAll().size());
	            System.out.print("查询所有数据");
	            List <Book> all=bookRepositoy.findAll();
	            for(Book b: all) {
	            	 System.out.print("title:"+b.getTitle()+","+"author:"+b.getAuthor()+","+"time:"+b.getNew_time()+"\n");
	            }
               
	            // 测试findByTitle, 查询书名为book5的书
                Assert.assertEquals("author5", bookRepositoy.findByTitle("book5").getAuthor());
                Book title=bookRepositoy.findByTitle("book5");
                System.out.print("所查询的书籍名："+title.getTitle()+"\n"+"查询结果："+title.getTitle()+","+title.getAuthor());

            // 测试findBook, 查询书名为book7的书
                Assert.assertEquals("author7", bookRepositoy.findBook("book7").getAuthor());
//
            // 测试findByTitleAndAuthor, 查询书名为book1,作者为author1的书
               Assert.assertEquals("author1", bookRepositoy.findByTitleAndAuthor("book1","author1").getAuthor());
                Book n=bookRepositoy.findByTitleAndAuthor("book1","author1");
                System.out.print("所查询的书籍名+作者名为："+n.getTitle()+","+n.getAuthor()+"\n"+"查询结果："+n.getTitle()+","+n.getAuthor()+","+n.getNew_time());
//
//	            // 测试findByTitleAndAuthor, 查询书名为book2,作者为author1的书
//	            Assert.assertEquals(null, bookRepositoy.findByTitleAndAuthor("book2", "author1"));
//
               //测试删除book3
	            bookRepositoy.delete(bookRepositoy.findBook("book3"));
	            List <Book> all2=bookRepositoy.findAll();
	            for(Book b: all2) {
	            	 System.out.print("title:"+b.getTitle()+","+"author:"+b.getAuthor()+","+"time:"+b.getNew_time()+"\n");
	            }
	            //测试修改
	            Book update=bookRepositoy.findBook("book5");
	            update.setAuthor("chenxiaoqing");
	            update.setNew_time(new Date());
	            update.setTitle("达芬奇 最后的晚餐");
	            bookRepositoy.save(update);
	            List <Book> all3=bookRepositoy.findAll();
	            for(Book b: all3) {
	            	 System.out.print("title:"+b.getTitle()+","+"author:"+b.getAuthor()+","+"time:"+b.getNew_time()+"\n");
	            }
//
//	            //测试删除是否成功
//	            Assert.assertEquals(9, bookRepositoy.findAll().size());

	           // 分页查询
	            bookRepositoy.deleteAll();
	           for (int i=1;i<=10;i++){
	            	bookRepositoy.save(new Book("book"+i,"Shaquila Chen",new Date()));
	            }
	            Sort sort = new Sort(Sort.Direction.DESC, "bookId");
	            int page = 1;
	            int size = 5;
	            Pageable pageable = new PageRequest(page, size, sort);
	            Page<Book> pages = bookRepositoy.findBookPage(pageable,"Shaquila Chen");

	            Iterator<Book> it= pages.iterator();

	            Assert.assertEquals(size,pages.getSize());
	            Assert.assertEquals(2,pages.getTotalPages());

	            while(it.hasNext()){
	                Book book = (Book)it.next();
	                System.out.println("title/author/createTime:"+book.getTitle()+"/"+book.getAuthor()+"/"+book.getNew_time());
	            }

	        }catch (Exception ex){
	            Assert.fail(ex.getMessage());
	        }
	    }

	    @Test
	    public void testBookStore(){
	        try{
	        	bookStoreRepository.deleteAll();

	            //生成5个书店
	            for(int i=1;i<=5;i++){
	            	bookStoreRepository.save(new BookStore("address_"+i,"manager_"+i));
	            }
	            // 测试findAll, 查询所有记录
	            Assert.assertEquals(5, bookStoreRepository.findAll().size());
	            Assert.assertEquals(1, bookStoreRepository.getBookStoreByManager("manager_2").size());
	            Assert.assertEquals("manager_2",bookStoreRepository.getBookStoreByManager("manager_2").get(0).getStoreManager());

	        }catch (Exception ex){
	            Assert.fail(ex.getMessage());
	        }
	    }
	

}
