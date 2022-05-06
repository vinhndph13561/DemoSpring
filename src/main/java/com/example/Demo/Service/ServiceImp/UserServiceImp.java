package com.example.Demo.Service.ServiceImp;

import com.example.Demo.Model.ResponseObject;
import com.example.Demo.Model.Role;
import com.example.Demo.Model.User;
import com.example.Demo.Model.UserRole;
import com.example.Demo.Repositories.RoleRepository;
import com.example.Demo.Repositories.UserRepository;
import com.example.Demo.Repositories.UserRoleRepository;
import com.example.Demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

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
        boolean exist2 = userRepository.existsById(id);
        if (exist2){
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
            user.setListUserRole(newUser.getListUserRole());
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
    public ResponseEntity<ResponseObject> saveUser(User newUser, int roleId) {
        User us = new User();
        UserRole ur = new UserRole();
        Role newRole = new Role();
        User foundUser = userRepository.findByEmail(newUser.getEmail().trim());
        if (foundUser!=null){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed","Email already used","")
            );
        }
        us.setEmail(newUser.getEmail());
        us.setPassword(passwordEncoder.encode(newUser.getPassword()));

        Role findRole = roleRepository.findById(roleId).map(role ->{
            ur.setRole(role);
            userRepository.save(us);
            return role;
        }).orElseGet(() ->{
            newRole.setId(0);
            newRole.setName("");
            return newRole;
        });
        if (findRole.getId()!=0){

            ur.setUser(userRepository.findByEmail(newUser.getEmail().trim()));
            userRoleRepository.save(ur);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","success",us)
            );

        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed","role not found","")
            );
        }


    }
}
