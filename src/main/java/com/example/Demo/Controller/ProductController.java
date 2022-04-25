package com.example.Demo.Controller;

import com.example.Demo.Model.Category;
import com.example.Demo.Model.Product;
import com.example.Demo.Model.ResponseObject;
import com.example.Demo.Repositories.CateRepository;
import com.example.Demo.Repositories.ProductRepository;
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
    private ProductRepository productRepository;

    @Autowired
    private CateRepository cateRepository;

    @GetMapping("")
    List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id){
        Optional<Product> foundProduct = productRepository.findById(id);
        if (foundProduct.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("oke","successfully",foundProduct)
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("oke","Can not find id" +id, "")
            );
        }
    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct){
        List<Product> foundProduct = productRepository.findByProductName(newProduct.getProductName().trim());
        if (foundProduct.size()>0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed","Email already used","")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","success",productRepository.save(newProduct))
        );
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct,@PathVariable Long id){
        Product updateProduct = productRepository.findById(id).map(product ->{
            product.setProductName(newProduct.getProductName());
            product.setQuantity(newProduct.getQuantity());
            product.setImage(newProduct.getImage());
            product.setPrice(newProduct.getPrice());
            product.setCategoryId(newProduct.getCategoryId());
            return productRepository.save(product);
        }).orElseGet(() ->{
            newProduct.setProductId(id);
            return productRepository.save(newProduct);
        });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","success",updateProduct)
        );
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id){
        boolean exist3 = productRepository.existsById(id);
        if (exist3){
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","success","")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed","Can not find user","")
        );
    }
}
