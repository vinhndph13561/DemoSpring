package com.example.Demo.Service.ServiceImp;

import com.example.Demo.Model.Category;
import com.example.Demo.Model.ResponseObject;
import com.example.Demo.Model.User;
import com.example.Demo.Repositories.CateRepository;
import com.example.Demo.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryServiceImp implements CategoryService {
    @Autowired
    private CateRepository cateRepository;
    @Override
    public List<Category> getAllCategories() {
        return cateRepository.findAll();
    }

    @Override
    public ResponseEntity<ResponseObject> getCategoryById(Long id) {
        Optional<Category> foundCate = cateRepository.findById(id);
        if (foundCate.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("oke","successfully",foundCate)
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("oke","Can not find id" +id, "")
            );
        }
    }

    @Override
    public ResponseEntity<ResponseObject> removeCategoryById(Long id) {
        boolean exist = cateRepository.existsById(id);
        if (!exist){
            cateRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","success","")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed","Can not find user","")
        );
    }

    @Override
    public ResponseEntity<ResponseObject> updateCategoryById(Category newCategory, Long id) {
        Category updateCate = cateRepository.findById(id).map(category ->{
            category.setCateName(newCategory.getCateName());

            return cateRepository.save(category);
        }).orElseGet(() ->{
            newCategory.setCateId(id);
            return cateRepository.save(newCategory);
        });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","success",updateCate)
        );
    }

    @Override
    public ResponseEntity<ResponseObject> saveCategory(Category newCategory) {
        List<Category> foundCate = cateRepository.findByCateName(newCategory.getCateName().trim());
        if (foundCate.size()>0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed","Email already used","")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","success",cateRepository.save(newCategory))
        );
    }
}
