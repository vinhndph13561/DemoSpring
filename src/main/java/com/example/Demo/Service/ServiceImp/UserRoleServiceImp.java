package com.example.Demo.Service.ServiceImp;

import com.example.Demo.Model.ResponseObject;
import com.example.Demo.Model.User;
import com.example.Demo.Model.UserRole;
import com.example.Demo.Repositories.UserRepository;
import com.example.Demo.Repositories.UserRoleRepository;
import com.example.Demo.Service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImp implements UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<ResponseObject> save(UserRole userRole) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","success",userRoleRepository.save(userRole))
        );
    }

    @Override
    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

    @Override
    public List<UserRole> findAllAdminOrDirector() {
        return userRoleRepository.findAllAdminOrDirector();
    }

    @Override
    public ResponseEntity<ResponseObject> delete(Integer id) {
        boolean exist4 = userRoleRepository.existsById(id);
        if (exist4){
            userRoleRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","success","")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed","Can not find id"+id,"")
        );
    }
}
