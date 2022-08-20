package com.example.shop.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private double price;

    private String description;

    private String color;

    private String photo;
}
