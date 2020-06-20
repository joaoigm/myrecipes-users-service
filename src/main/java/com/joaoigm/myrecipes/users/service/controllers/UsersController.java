package com.joaoigm.myrecipes.users.service.controllers;

import com.joaoigm.myrecipes.users.service.models.User;
import com.joaoigm.myrecipes.users.service.repositories.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    private IUserRepository userRepository;

    @GetMapping
    public @ResponseBody Iterable<User> getUsers(){

        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody User getUser(@PathVariable("id") Integer id){
        return userRepository.findById(id).get();
    }

    @PostMapping
    public @ResponseBody User createUser(@RequestBody User user){
        try {
            user.generateNewPassword();
            return userRepository.save(user);
        }
        catch (Exception e) { throw e; }
    }

    @PutMapping
    public @ResponseBody User updateUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object deleteUser(@PathVariable("id")Integer id){
        if(!userRepository.existsById(id)){ return Collections.singletonMap("message", "User with id "+id+" doesn't exist"); }
        userRepository.deleteById(id);
        return Collections.singletonMap("message", "User deleted successful");
    }

}