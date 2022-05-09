package com.example.Demo.Service;

import com.example.Demo.Model.ResponseObject;
import com.example.Demo.Model.UserRole;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserRoleService{

	ResponseEntity<ResponseObject> save(UserRole userRole);

	List<UserRole> findAll();

	List<UserRole> findAllAdminOrDirector();

	ResponseEntity<ResponseObject> delete(Integer id);
	
}
