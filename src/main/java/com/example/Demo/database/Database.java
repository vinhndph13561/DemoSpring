//package com.example.Demo.database;
//
//import com.example.Demo.Model.Category;
//import com.example.Demo.Model.User;
//import com.example.Demo.Repositories.CateRepository;
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
//    CommandLineRunner initDatabase(UserRepository userRepository){
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//                User userA = new User("Nguyen Duc Vinh","Vinh@gmail.com","123456","0345401309","vinh.jpg",25,true,true);
//                User userB = new User("Nguyen Duc Minh","Minh@gmail.com","123456","0345401308","minh.jpg",25,true,false);
//                User userC = new User("Nguyen Thuy Linh","Linh@gmail.com","123456","0345401307","linh.jpg",25,false,false);
//                logger.info("insert data: "+ userRepository.save(userA));
//                logger.info("insert data: "+ userRepository.save(userB));
//                logger.info("insert data: "+ userRepository.save(userC));
//            }
//        };
//    }
//
//
//}
