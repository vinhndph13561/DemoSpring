package com.example.Demo.Repositories;


import com.example.Demo.Model.User;
import com.example.Demo.Model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);


}