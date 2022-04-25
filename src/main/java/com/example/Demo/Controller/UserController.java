package com.example.Demo.Controller;


import com.example.Demo.Model.ResponseObject;
import com.example.Demo.Model.User;
import com.example.Demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/controller/users")
public class UserController {
    @Autowired
    private UserRepository repository;

    @GetMapping("")
    List<User> getAllUsers(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id){
        Optional<User> foundUser = repository.findById(id);
        if (foundUser.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("oke","successfully",foundUser)
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("oke","Can not find id" +id, "")
            );
        }
    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertUser(@RequestBody User newUser){
        List<User> foundUsers = repository.findByEmail(newUser.getEmail().trim());
        if (foundUsers.size()>0){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed","Email already used","")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","success",repository.save(newUser))
        );
    }

    @PutMapping("/update/{id}")
    ResponseEntity<ResponseObject> updateUser(@RequestBody User newUser,@PathVariable Long id){
            User updateUser = repository.findById(id).map(user ->{
                user.setName(newUser.getName());
                user.setEmail(newUser.getEmail());
                user.setPassword(newUser.getPassword());
                user.setPhoneNumber(newUser.getPhoneNumber());
                user.setAvatar(newUser.getAvatar());
                user.setAge(newUser.getAge());
                user.setGender(newUser.isGender());
                user.setRole(newUser.isRole());
                return repository.save(user);
            }).orElseGet(() ->{
                newUser.setId(id);
                return repository.save(newUser);
            });
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","success",updateUser)
            );

    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteUser(@PathVariable Long id){
        boolean exist = repository.existsById(id);
        if (exist){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","success","")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed","Can not find user","")
        );
    }
}
