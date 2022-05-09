package com.example.Demo.Controller;

import com.example.Demo.Model.ResponseObject;
import com.example.Demo.Model.User;
import com.example.Demo.Service.ServiceImp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/controller/admin")
public class AdminController {
    @Autowired
    UserServiceImp userServiceImp;

    @GetMapping("/all")
    List<User> getAllUsers(){
        return userServiceImp.getAllUser();
    }

    @GetMapping("/{email}")
    ResponseEntity<ResponseObject> findUserByEmail(@PathVariable String email){
        return userServiceImp.getUserByEmail(email);
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateUser(@RequestBody User newUser,@PathVariable int id){
        return userServiceImp.updateUserById(newUser,id);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteUser(@PathVariable int id){
        return userServiceImp.removeUserById(id);
    }

    @GetMapping("/id/{id}")
    ResponseEntity<ResponseObject> findUserById(@PathVariable int id){
        return userServiceImp.getUserById(id);
    }
}
