package com.example.Demo.Service;

import com.example.Demo.Model.ResponseObject;
import com.example.Demo.Model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    ResponseEntity<ResponseObject> getUserById(int id);
    ResponseEntity<ResponseObject> getUserByEmail(String email);
    ResponseEntity<ResponseObject> removeUserById(int id);
    ResponseEntity<ResponseObject> updateUserById(User newUser, int id);
    ResponseEntity<ResponseObject> saveUser(User newUser);
}
