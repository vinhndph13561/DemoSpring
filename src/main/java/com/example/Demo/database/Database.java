//package com.example.Demo.database;
//
//import com.example.Demo.Model.Category;
//import com.example.Demo.Model.Role;
//import com.example.Demo.Model.User;
//import com.example.Demo.Repositories.CateRepository;
//import com.example.Demo.Repositories.RoleRepository;
//import com.example.Demo.Repositories.UserRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//
//
//@Configuration
//public class Database {
//    private static final Logger logger = LoggerFactory.getLogger(Database.class);
//
//    @Bean
//    CommandLineRunner initDatabase(RoleRepository roleRepository){
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//                Role roleA= new Role("ROLE_MEMBER");
//                Role roleB= new Role("ROLE_ADMIN");
//                Role roleC= new Role("ROLE_DIRECTOR");
//                logger.info("insert data: "+ roleRepository.save(roleA));
//                logger.info("insert data: "+ roleRepository.save(roleB));
//                logger.info("insert data: "+ roleRepository.save(roleC));
//            }
//        };
//    }
//
//
//}
