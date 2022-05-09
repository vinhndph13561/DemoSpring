package com.example.Demo.Controller;

import com.example.Demo.Model.ResponseObject;
import com.example.Demo.Model.User;
import com.example.Demo.Service.LoginService;
import com.example.Demo.Service.ServiceImp.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/controller/login")
public class LoginController {
    @Autowired
    private LoginServiceImp loginServiceImp;
    @GetMapping("")
    public ResponseEntity<ResponseObject> loginSuccess() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("oke","successfully","")
        );
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(@RequestBody User user) {
        return loginServiceImp.login(user);
    }
}
