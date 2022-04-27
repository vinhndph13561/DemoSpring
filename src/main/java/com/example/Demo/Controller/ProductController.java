package com.example.Demo.Controller;

import com.example.Demo.Model.Category;
import com.example.Demo.Model.Product;
import com.example.Demo.Model.ResponseObject;
import com.example.Demo.Service.ServiceImp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/controller/products")
public class ProductController {
    @Autowired
    private ProductServiceImp productServiceImp;

    @GetMapping("")
    List<Product> getAllProducts(){
        return productServiceImp.getAllProduct();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id){
        return productServiceImp.getProductById(id);
    }

    @GetMapping("/{proName}")
    ResponseEntity<ResponseObject> findById(@PathVariable String proName){
        return productServiceImp.getProductByProductName(proName);
    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct){
        return productServiceImp.saveProduct(newProduct);
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct,@PathVariable Long id){
        return productServiceImp.updateProductById(newProduct,id);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id){
        return productServiceImp.removeProductById(id);
    }
}
