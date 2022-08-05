package com.example.shop.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    private String password;

    private int age;

    private double balance;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "roles")
    private Role role;
}
