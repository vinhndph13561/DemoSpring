package com.example.Demo.Service;

import com.example.Demo.Model.ResponseObject;
import com.example.Demo.Model.Role;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoleService {
    ResponseEntity<ResponseObject> findRoleById(int roleId);


}
