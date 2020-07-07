package com.joaoigm.myrecipes.users.service.controllers;

import com.joaoigm.myrecipes.users.service.models.User;
import com.joaoigm.myrecipes.users.service.repositories.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UsersController {
    @Autowired
    private IUserRepository userRepository;

    @GetMapping
    public @ResponseBody Iterable<User> getUsers(){

        return userRepository.findAll();
    }

    @GetMapping("/authenticated")
    public boolean getUserAuthenticated(@RequestParam UUID id){
        return userRepository.existsById(id);
    }

    @GetMapping("/{id}")
    public @ResponseBody User getUser(@PathVariable("id") UUID id){
        return userRepository.findById(id).get();
    }

    @PostMapping
    public @ResponseBody User createUser(@RequestBody User user){
        try {
            user.generateNewPassword();
            user.generateId();
            return userRepository.save(user);
        }
        catch (Exception e) { throw e; }
    }

    @PostMapping("authenticate")
    public @ResponseBody
    ResponseEntity<User> authenticate(@RequestBody User user){
        User savedUser = userRepository.findByEmail(user.getEmail());
        if(savedUser != null) {
            String savedPassword = savedUser.getPassword();
            String passedPassword = user.getPassword();
            if(savedPassword.equals(passedPassword)){
               return ResponseEntity.ok(savedUser);
            } else
            {
                ResponseEntity.status(401).body(null);
            }
        }
        return ResponseEntity.status(500).body(null);
    }

    @PutMapping
    public @ResponseBody User updateUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object deleteUser(@PathVariable("id")UUID id){
        if(!userRepository.existsById(id)){ return Collections.singletonMap("message", "User with id "+id+" doesn't exist"); }
        userRepository.deleteById(id);
        return Collections.singletonMap("message", "User deleted successful");
    }

}