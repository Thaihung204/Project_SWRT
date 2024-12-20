package com.example.SV_Market.controller;

import com.example.SV_Market.entity.User;
import com.example.SV_Market.response.ReportResponse;
import com.example.SV_Market.service.ReportService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.SV_Market.request.SensorProductRequest;
import com.example.SV_Market.service.ProductService;
import com.example.SV_Market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController

@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;


    @GetMapping("/pending")
    public ResponseEntity<?> getPendingProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.sensorProduct());
    }

    //duyet san pham
    @PutMapping("/product")
    public ResponseEntity<?> updateStatusProducts(@RequestBody SensorProductRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.acceptProduct(request));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    //ban user
    @PutMapping("/banUser")
    public ResponseEntity<?> banUser(@RequestParam(value = "userId") String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.banUser(userId));
    }
    @PutMapping("/unbanUser")
    public ResponseEntity<?> unbanUser(@RequestParam(value = "userId") String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.unbanUser(userId));
    }


     private ReportService reportService;

//   @GetMapping()
//   public ResponseEntity<List<ReportResponse>> Getviewreports() {
//       List<ReportResponse> viewreports = reportService.viewHistoryReport();
//       return ResponseEntity.ok(viewreports);
//   }
}
