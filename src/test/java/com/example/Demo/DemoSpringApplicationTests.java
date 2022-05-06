package com.example.Demo;

import com.example.Demo.Model.Category;
import com.example.Demo.Repositories.CateRepository;
import com.example.Demo.Service.ServiceImp.CategoryServiceImp;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class DemoSpringApplicationTests {

    @Mock
    CateRepository cateRepository;
    @InjectMocks
    CategoryServiceImp categoryServiceImp;

    @Test
    void contextLoads() {
    }
    @Test
    void whenGetAll_shouldReturnList() {
        // 1. create mock data
        List<Category> mockProducts = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            mockProducts.add(new Category(i));
        }

        // 2. define behavior of Repository
        when(cateRepository.findAll()).thenReturn(mockProducts);

        // 3. call service method
        List<Category> actualProducts = categoryServiceImp.getAllCategories();

        // 4. assert the result

        assertThat(actualProducts.size()).isEqualTo(mockProducts.size());

        // 4.1 ensure repository is called
        verify(cateRepository).findAll();
    }
}
