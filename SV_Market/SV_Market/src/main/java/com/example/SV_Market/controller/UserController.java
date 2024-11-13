package com.example.SV_Market.controller;

import com.example.SV_Market.dto.UserDto;
import com.example.SV_Market.request.UserUpdateRequest;
import com.example.SV_Market.entity.User;
import com.example.SV_Market.request.LoginRequest;
import com.example.SV_Market.request.UpgradeRequest;
import com.example.SV_Market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public User createUser(@RequestBody UserDto request) {
        return userService.createUser(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> checkLogin(@RequestBody LoginRequest loginRequest) {
        try {
            User user = userService.getUserByEmailPass(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(user); // 200 OK with user data
        } catch (RuntimeException e) {
            // Return 404 Not Found if the user is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/byid/{userId}")
    User getUser(@PathVariable String userId){
        return userService.getUserById(userId);
    }
    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody UserUpdateRequest request) {
        try {
            return ResponseEntity.ok(userService.updatePassword(request.getEmail(), request.getCurrentPassword(), request.getNewPassword()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@ModelAttribute UserUpdateRequest request) throws IOException {
        try {
            userService.updateUser(request);
            return ResponseEntity.ok("Edit profile successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/upgrade")
    public ResponseEntity<?> upgradeUserRole(@RequestBody UpgradeRequest upgradeRequest) {
        try {
            userService.upgradeUserRole(upgradeRequest);
            return ResponseEntity.ok("upgrade account succesfull");
        } catch (Exception e) {
            return ResponseEntity.ok("upgrade account fail");
        }

    }


    @MessageMapping("/chatuser.addUser")
    @SendTo("/user/public")
    public User addUser(
            @Payload User user
    ) {
        userService.saveUser(user);
        return user;
    }



}

