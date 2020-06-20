package com.joaoigm.myrecipes.users.service.models;

import com.joaoigm.myrecipes.utils.PasswordGenerator;

import javax.persistence.*;
import java.lang.annotation.RetentionPolicy;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true)
    private String email;
    private String password;

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void generateNewPassword(){
        this.setPassword(PasswordGenerator.generateRandomPassword(8));
    }
}