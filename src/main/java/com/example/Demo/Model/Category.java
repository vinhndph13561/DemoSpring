package com.example.Demo.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cateId", nullable = false)
    private int cateId;

    private String cateName;
    public  Category(int i, String watch){}

    public Category(String cateName) {
        this.cateName = cateName;

    }

    public Category(int i) {
        this.cateId= cateId;
    }

    public Category() {

    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }



    @Override
    public String toString() {
        return "Category{" +
                "cateId=" + cateId +
                ", cateName='" + cateName + '\'' +
                '}';
    }
}
