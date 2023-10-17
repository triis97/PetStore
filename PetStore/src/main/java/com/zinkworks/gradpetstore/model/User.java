package com.zinkworks.gradpetstore.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userTable")
@Data
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "firstName")
    private String firstName;

    @Column(name =  "lastName")
    private String lastName;

    @Column(name =  "email")
    private String email;

    @Column(name =  "password")
    private String password;

    @Column(name =  "phone")
    private String phone;

    @Column(name =  "userStatus")
    private Integer userStatus;


}
