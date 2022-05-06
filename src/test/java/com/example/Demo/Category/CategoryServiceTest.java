package com.example.Demo.Category;

import com.example.Demo.Exception.CategoryNotFoundException;
import com.example.Demo.Model.Category;
import com.example.Demo.Repositories.CateRepository;
import com.example.Demo.Service.CategoryService;
import com.example.Demo.Service.ServiceImp.CategoryServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @Mock
    CateRepository categoryRepository;
    @InjectMocks
    CategoryServiceImp categoryServiceImp;
    @Test
    void whenGetAll_shouldReturnList() {
        // 1. create mock data
        List<Category> mockcate = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            mockcate.add(new Category(i));
        }
        // 2. define behavior of Repository
        when(categoryRepository.findAll()).thenReturn(mockcate);
        // 3. call service method
        List<Category> actualProducts = categoryServiceImp.getAllCategories();
        // 4. assert the result
        assertThat(actualProducts.size()).isEqualTo(mockcate.size());
        // 4.1 ensure repository is called
        verify(categoryRepository).findAll();
    }
    @Test
    void whenGetInvalidOne_shouldThrowException() {
        Integer invalidCate = 1;

        when(categoryRepository.findById(any(Integer.class))).thenReturn(Optional.ofNullable(null));

        assertThatThrownBy(() -> categoryServiceImp.getCategoryById(invalidCate))
                .isInstanceOf(CategoryNotFoundException.class);

        verify(categoryRepository).findById(any(Integer.class));
    }
}
