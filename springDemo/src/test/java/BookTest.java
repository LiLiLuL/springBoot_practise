import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.springDemo.SpringDemoApplication;
import com.example.springDemo.controller.BookController;
import com.example.springDemo.domain.Book;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MockServletContext.class)
//@SpringBootTest(classes = SpringDemoApplication.class)
@WebAppConfiguration
public class BookTest {

    private MockMvc mvc;

    private RequestBuilder request = null;


    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new BookController()).build();
        request = null;
    }

    public void testGet() throws Exception{
        request = get("/books/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
        String respStr = mvc.perform(get("/books/"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")))
                .andReturn().getResponse().getContentAsString();
        System.out.println("testGet.resp:"+respStr);
    }

    public void testPost() throws Exception{
        Book book = new Book();
        book.setBookId(Long.parseLong("33"));
        book.setTitle("Spring boot的单元测试");
        book.setAuthor("chenxiaoqing");
        Book book1=new Book();
        book1.setBookId(Long.parseLong("34"));
        book1.setTitle("第二本书");
        book1.setAuthor("cxq");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBook = objectMapper.writeValueAsString(book);
        String jsonBook1 = objectMapper.writeValueAsString(book1);

        request = post("/books/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBook.getBytes());

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("success")));

        String respStr = mvc.perform(get("/books/"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[" + jsonBook + "]")))
                .andReturn().getResponse().getContentAsString();
        System.out.println("testPost.resp:"+respStr);
    }

    public void testPut() throws Exception{
        Book book = new Book();
        book.setBookId(Long.parseLong("33"));
        book.setTitle("Spring Boot学习教程");
        book.setAuthor("ChenXQ");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBook = objectMapper.writeValueAsString(book);

        request = put("/books/" + book.getBookId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBook.getBytes());

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("success")));

        String respStr = mvc.perform(get("/books/"+book.getBookId()))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(jsonBook)))
                .andReturn().getResponse().getContentAsString();
        System.out.println("testPut.resp:"+respStr);
    }

    public void testDelete() throws Exception{
        request = delete("/books/33");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("success")));

        String respStr = mvc.perform(get("/books/"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")))
                .andReturn().getResponse().getContentAsString();
        System.out.println("testDelete.resp:"+respStr);

    }

    @Test
    public void testSuite() throws Exception{
        this.testGet();//获取一本书籍
        this.testPost();//创建一本书籍
        this.testPut();//更新一本书籍
        this.testDelete();//删除一本书籍
    }
}
