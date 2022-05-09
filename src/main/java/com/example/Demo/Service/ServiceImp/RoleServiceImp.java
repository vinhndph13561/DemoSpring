package com.example.Demo.Service.ServiceImp;

import com.example.Demo.Model.ResponseObject;
import com.example.Demo.Model.Role;
import com.example.Demo.Model.User;
import com.example.Demo.Repositories.RoleRepository;
import com.example.Demo.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public ResponseEntity<ResponseObject> findRoleById(int roleId) {
        Optional<Role> foundRole = roleRepository.findById(roleId);
        if (foundRole.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("oke","successfully",foundRole)
            );
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("oke","Can not find id" +roleId, "")
            );
        }
    }

}
