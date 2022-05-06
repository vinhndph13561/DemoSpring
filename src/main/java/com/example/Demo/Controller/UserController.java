package com.example.Demo.Controller;

import com.example.Demo.Model.ResponseObject;
import com.example.Demo.Model.User;
import com.example.Demo.Service.ServiceImp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/controller/users")
public class UserController {

    @Autowired
    UserServiceImp userServiceImp;

    @GetMapping("/{email}")
    ResponseEntity<ResponseObject> findUserByEmail(@PathVariable String email){
        return userServiceImp.getUserByEmail(email);
    }

    @PostMapping("/insert/{id}")
    ResponseEntity<ResponseObject> insertUser(@RequestBody User newUser,@PathVariable int id){
        return userServiceImp.saveUser(newUser,id);
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateUser(@RequestBody User newUser,@PathVariable int id){
        return userServiceImp.updateUserById(newUser,id);
    }


}
