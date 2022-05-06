package com.example.Demo.Repositories;

import com.example.Demo.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CateRepository extends JpaRepository<Category,Integer> {
    List<Category> findByCateName(String trim);
}
