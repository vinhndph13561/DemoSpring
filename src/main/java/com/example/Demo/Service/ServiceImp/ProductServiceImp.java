package com.example.Demo.Service.ServiceImp;

import com.example.Demo.Model.Product;
import com.example.Demo.Model.ResponseObject;
import com.example.Demo.Repositories.CateRepository;
import com.example.Demo.Repositories.ProductRepository;
import com.example.Demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public ResponseEntity<ResponseObject> getProductById(Long id) {
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

    @Override
    public ResponseEntity<ResponseObject> getProductByProductName(String proName) {
        List<Product> foundProduct = productRepository.findByProductName(proName);
        if (foundProduct.size()>0){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("oke","successfully",foundProduct)
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("oke","Can not find product" +proName, "")
            );
        }
    }

    @Override
    public ResponseEntity<ResponseObject> removeProductById(Long id) {
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

    @Override
    public ResponseEntity<ResponseObject> updateProductById(Product newProduct, Long id) {
        Product updateProduct = productRepository.findById(id).map(product ->{
            product.setProductName(newProduct.getProductName());
            product.setQuantity(newProduct.getQuantity());
            product.setImage(newProduct.getImage());
            product.setPrice(newProduct.getPrice());
            product.setCategory(newProduct.getCategory());
            return productRepository.save(product);
        }).orElseGet(() ->{
            newProduct.setProductId(id);
            return productRepository.save(newProduct);
        });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","success",updateProduct)
        );
    }

    @Override
    public ResponseEntity<ResponseObject> saveProduct(Product newProduct) {
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
}
