package com.example.Demo.Service.ServiceImp;

import com.example.Demo.Model.ResponseObject;
import com.example.Demo.Model.User;
import com.example.Demo.Repositories.UserRepository;
import com.example.Demo.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImp implements LoginService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Override
    public ResponseEntity<ResponseObject> login(User user) {
        User user2 = userRepository.findByEmail(user.getEmail());

        // response
        if (user2 == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed","pls login again","")
            );
        }else if (passwordEncoder.matches(user.getPassword(),user2.getPassword())) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "success", user)
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed","pass is wrong","")
            );
        }
    }
}
