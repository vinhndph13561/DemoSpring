package com.example.Demo.Service;

import com.example.Demo.Model.ResponseObject;
import com.example.Demo.Model.User;
import org.springframework.http.ResponseEntity;

public interface LoginService {
    ResponseEntity<ResponseObject> login(User user);
}
