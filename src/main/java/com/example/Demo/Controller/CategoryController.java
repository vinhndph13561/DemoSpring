package com.example.Demo.Controller;

import com.example.Demo.Model.Category;
import com.example.Demo.Model.ResponseObject;
import com.example.Demo.Model.User;
import com.example.Demo.Repositories.CateRepository;
import com.example.Demo.Repositories.UserRepository;
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
    private UserRepository userRepository;

    @Autowired
    private CateRepository cateRepository;

    @GetMapping("")
    List<Category> getAllCates(){
        return cateRepository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id){
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

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertCate(@RequestBody Category newCate){
        List<Category> foundCates = cateRepository.findByCateName(newCate.getCateName().trim());
        if (foundCates.size()>0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed","Email already used","")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","success",cateRepository.save(newCate))
        );
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateCate(@RequestBody Category newCate,@PathVariable Long id){
        Category updateCate = cateRepository.findById(id).map(category ->{
            category.setCateName(newCate.getCateName());
            category.setUserId(newCate.getUserId());
            return cateRepository.save(category);
        }).orElseGet(() ->{
            newCate.setCateId(id);
            return cateRepository.save(newCate);
        });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","success",updateCate)
        );
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteCate(@PathVariable Long id){
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
}
