//package com.example.Demo.database;
//
//import com.example.Demo.Model.Category;
//import com.example.Demo.Repositories.CateRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class cateDatabase {
//    private static final Logger logger = LoggerFactory.getLogger(Database.class);
//    @Bean
//    CommandLineRunner initDatabase2(CateRepository cateRepository){
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//                Category categoryA = new Category("Laptop");
//
//                Category categoryB = new Category("PC");
//                Category categoryC = new Category("Mobile");
//                logger.info("insert data: "+ cateRepository.save(categoryA));
//                logger.info("insert data: "+ cateRepository.save(categoryB));
//                logger.info("insert data: "+ cateRepository.save(categoryC));
//            }
//        };
//    }
//}
