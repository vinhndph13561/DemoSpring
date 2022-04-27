package com.example.Demo.Controller;

import com.example.Demo.Model.ResponseObject;
import com.example.Demo.Model.User;
import com.example.Demo.Service.ServiceImp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/controller/users")
public class UserController {
    @Autowired
    UserServiceImp userServiceImp;

    @GetMapping("")
    List<User> getAllUsers(){
        return userServiceImp.getAllUser();
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findUserById(@PathVariable int id){
        return userServiceImp.getUserById(id);
    }

    @GetMapping("/{email}")
    ResponseEntity<ResponseObject> findUserByEmail(@PathVariable String email){
        return userServiceImp.getUserByEmail(email);
    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertUser(@RequestBody User newUser){
        return userServiceImp.saveUser(newUser);
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateUser(@RequestBody User newUser,@PathVariable int id){
        return userServiceImp.updateUserById(newUser,id);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteUser(@PathVariable int id){
        return userServiceImp.removeUserById(id);
    }

    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }
}
