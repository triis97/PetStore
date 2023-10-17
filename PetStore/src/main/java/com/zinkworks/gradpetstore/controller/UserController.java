package com.zinkworks.gradpetstore.controller;

import com.zinkworks.gradpetstore.exceptions.PetNotFoundException;
import com.zinkworks.gradpetstore.model.Pet;
import com.zinkworks.gradpetstore.model.User;
import com.zinkworks.gradpetstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            MultiValueMap<String, String> headers = new HttpHeaders();
            headers.add("PetId", String.valueOf(createdUser.getId()));
            return new ResponseEntity<>(
                    user,
                    headers,
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(@PathParam("username") String username) {
        try {
            return new ResponseEntity<>(
                    userService.getByUsername(username),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/user")
    public ResponseEntity<User> deleteUser(@PathParam("username") String username) {
        try {
            userService.deleteByUsername(username);
            return new ResponseEntity<>(
                    null,
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
