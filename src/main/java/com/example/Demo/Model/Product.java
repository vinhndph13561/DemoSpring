package com.example.Demo.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="productId",nullable = false)
    private Long productId;

    @Column(unique = true,nullable = false,length = 255)
    private String productName;
    @Column(name="quantity",nullable = false)
    private int quantity;
    @Column(name="image",nullable = false)
    private String image;
    @Column(name="price",nullable = false)
    private Double price;
    @Column(name="color",nullable = false)
    private String color;
    @Column(name="size",nullable = false)
    private String size;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cateId", referencedColumnName = "cateId")
    private Category category;

    public Product(String productName, int quantity, String image, Double price, String color, String size, Category category) {
        this.productName = productName;
        this.quantity = quantity;
        this.image = image;
        this.price = price;
        this.color = color;
        this.size = size;
        this.category = category;
    }

    public Product() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", category=" + category +
                '}';
    }
}
