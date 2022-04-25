package com.example.Demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cateId;
    private String cateName;

    private Long userId;


    public  Category(){}

    public Category( String cateName, Long userId) {
        this.cateName = cateName;
        this.userId = userId;
    }

    public Long getCateId() {
        return cateId;
    }

    public void setCateId(Long cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cateId=" + cateId +
                ", cateName='" + cateName + '\'' +
                ", userId=" + userId +
                '}';
    }
}
