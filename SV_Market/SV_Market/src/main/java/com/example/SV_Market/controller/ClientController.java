package com.example.SV_Market.controller;

import com.example.SV_Market.dto.UserDto;
import com.example.SV_Market.dto.UserUpdateRequest;
import com.example.SV_Market.dto.sdi.ClientSdi;
import com.example.SV_Market.entity.User;
import com.example.SV_Market.service.ClientService;
import com.example.SV_Market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin("*")
    @RequestMapping("/client")
public class ClientController {


    @Autowired
    private ClientService clientService;
    @Autowired
    private UserService userService;
    //gui mail den email duoc gui kem
    @PostMapping("/signup")
    public ResponseEntity<Boolean> create(@RequestBody ClientSdi sdi) {
        return ResponseEntity.ok(clientService.create(sdi));
    }
    @PostMapping("/verify")
    User creatUser(@RequestBody UserDto request){
        return userService.createUser(request);
    }

    //get otp theo email
    @GetMapping("/{email}")
    public String getOtp(@PathVariable("email") String email){
        return clientService.layOtp(email);
    }

    @PutMapping("/changepassword")
    public ResponseEntity<String> changePassword(@RequestBody UserUpdateRequest request) {
        try {
            return ResponseEntity.ok(userService.updatePassword(request.getEmail(), request.getCurrentPassword(), request.getNewPassword()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/update")
    public User updateUser(@ModelAttribute UserUpdateRequest request) throws IOException {
        try {
            User user=  userService.updateUser(request);
            return user;
        } catch (Exception e) {
            return null;
        }
    }
}