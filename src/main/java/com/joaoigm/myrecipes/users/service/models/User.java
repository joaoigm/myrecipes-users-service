package com.joaoigm.myrecipes.users.service.models;

import com.joaoigm.myrecipes.utils.PasswordGenerator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "users")
public class User {

    @Id
    private UUID id;
    private String email;
    private String password;

    

    public UUID getId() {
        return id;
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

    public void generateId(){ this.id = UUID.randomUUID(); }

    public void setPassword(String password) {
        this.password = password;
    }

    public void generateNewPassword(){
        this.setPassword(PasswordGenerator.generateRandomPassword(8));
    }
}