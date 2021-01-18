package com.project.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity @Data @NoArgsConstructor @ToString
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String roles;
    @Column(columnDefinition="tinyint(1) default 1")
    private Boolean active;

    public User(int id, String email, String password, String role, Boolean active) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = role;
        this.active = active;
    }
}
