package com.example.Demo.Service;

import com.example.Demo.Model.Category;
import com.example.Demo.Model.Product;
import com.example.Demo.Model.ResponseObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();
    ResponseEntity<ResponseObject> getProductById(Long id);
    ResponseEntity<ResponseObject> getProductByProductName(String proName);
    ResponseEntity<ResponseObject> removeProductById(Long id);
    ResponseEntity<ResponseObject> updateProductById(Product newProduct, Long id);
    ResponseEntity<ResponseObject> saveProduct(Product newProduct);
}
