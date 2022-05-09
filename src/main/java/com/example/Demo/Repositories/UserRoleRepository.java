package com.example.Demo.Repositories;

import com.example.Demo.Model.Role;
import com.example.Demo.Model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole,Integer> {
    @Query("SELECT u FROM UserRole u WHERE (u.role.id = 2 or u.role.id = 3)")
    List<UserRole> findAllAdminOrDirector();

}
