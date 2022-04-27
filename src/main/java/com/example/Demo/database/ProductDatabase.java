//package com.example.Demo.database;
//
//import com.example.Demo.Model.Category;
//import com.example.Demo.Model.Product;
//import com.example.Demo.Repositories.CateRepository;
//import com.example.Demo.Repositories.ProductRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ProductDatabase {
//    @Autowired
//    private CateRepository cateRepository;
//    private static final Logger logger = LoggerFactory.getLogger(Database.class);
//    @Bean
//    CommandLineRunner initDatabase3(ProductRepository productRepository){
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//                Product prodA = new Product("Dell Latitue",20,"dell.jpg",1500000.0,"Black","16 inches",);
//                Product prodB = new Product("HP 5580",10,"hp.jpg",1700000.0,"Black","16 inches",);
//                Product prodC = new Product("Iphone 13 ProMax",15,"Iphone.jpg",2000000.0,"Black","16 inches",3L);
//                logger.info("insert data: "+ productRepository.save(prodA));
//                logger.info("insert data: "+ productRepository.save(prodB));
//                logger.info("insert data: "+ productRepository.save(prodC));
//            }
//        };
//    }
//}
