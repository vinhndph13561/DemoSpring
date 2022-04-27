package com.example.Demo.Controller;

import com.example.Demo.Model.Category;
import com.example.Demo.Model.ResponseObject;
import com.example.Demo.Service.ServiceImp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/controller/categories")
public class CategoryController {

    @Autowired
    private CategoryServiceImp categoryServiceImp;

    @GetMapping("")
    List<Category> getAllCates(){
        return categoryServiceImp.getAllCategories();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id){
        return categoryServiceImp.getCategoryById(id);
    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertCate(@RequestBody Category newCate){
        return categoryServiceImp.saveCategory(newCate);
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateCate(@RequestBody Category newCate,@PathVariable Long id){
        return categoryServiceImp.updateCategoryById(newCate,id);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteCate(@PathVariable Long id){
       return categoryServiceImp.removeCategoryById(id);
    }
}
