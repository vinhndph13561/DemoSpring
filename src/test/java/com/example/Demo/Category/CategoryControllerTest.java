package com.example.Demo.Category;

import com.example.Demo.Model.Category;
import com.example.Demo.Service.ServiceImp.CategoryServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class CategoryControllerTest {
    @MockBean
    MockMvc mvc;
    @Autowired
    private WebApplicationContext context;
    @MockBean
    CategoryServiceImp categoryServiceImp;
    private List<Category> allCategory;
    private Category category1;
    private Category category2;

    @Before
    public void setup(){

        category1 = new Category(1,"Watch");
        category2 = new Category(2,"Ipad");
        allCategory.add(category1);
        allCategory.add(category2);
    }

    @Test
    public void testFindAll() throws Exception {
//        List<Product> allProduct = IntStream.range(0, 10)
//                .mapT  oObj(i -> new Product(i))
//                .collect(Collectors.toList());

        given(categoryServiceImp.getAllCategories()).willReturn(allCategory);

        mvc.perform(get("/api/products").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", hasSize(2)));
    }
}
