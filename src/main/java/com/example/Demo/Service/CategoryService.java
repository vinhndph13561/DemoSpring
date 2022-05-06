package com.example.Demo.Service;

import com.example.Demo.Model.Category;
import com.example.Demo.Model.ResponseObject;
import com.example.Demo.Model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    ResponseEntity<ResponseObject> getCategoryById(int id);
    ResponseEntity<ResponseObject> removeCategoryById(int id);
    ResponseEntity<ResponseObject> updateCategoryById(Category newCategory, int id);
    ResponseEntity<ResponseObject> saveCategory(Category newCategory);
}
