package com.example.Demo.Service.ServiceImp;

import com.example.Demo.Model.ResponseObject;
import com.example.Demo.Model.User;
import com.example.Demo.Repositories.UserRepository;
import com.example.Demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public ResponseEntity<ResponseObject> getUserById(int id) {
        Optional<User> foundUser = userRepository.findById(id);
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

    @Override
    public ResponseEntity<ResponseObject> getUserByEmail(String email) {
        User foundUser = userRepository.findByEmail(email);
        if (foundUser!=null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("oke","successfully",foundUser)
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("oke","Can not find product" +email, "")
            );
        }
    }

    @Override
    public ResponseEntity<ResponseObject> removeUserById(int id) {
        boolean exist = userRepository.existsById(id);
        if (exist){
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","success","")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed","Can not find user","")
        );
    }

    @Override
    public ResponseEntity<ResponseObject> updateUserById(User newUser, int id) {
        User updateUser = userRepository.findById(id).map(user ->{
            user.setEmail(newUser.getEmail());
            user.setPassword(newUser.getPassword());
            user.setRoles(newUser.getRoles());
            return userRepository.save(user);
        }).orElseGet(() ->{
            newUser.setId(id);
            return userRepository.save(newUser);
        });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","success",updateUser)
        );
    }




    @Override
    public ResponseEntity<ResponseObject> saveUser(User newUser) {
        User foundUser = userRepository.findByEmail(newUser.getEmail().trim());
        if (foundUser!=null){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed","Email already used","")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","success",userRepository.save(newUser))
        );
    }
}
