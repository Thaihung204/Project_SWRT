package com.example.SV_Market.controller;

import com.example.SV_Market.request.SensorProductRequest;
import com.example.SV_Market.service.ProductService;
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
    @GetMapping("/pending")
    public ResponseEntity<?> getPendingProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.sensorProduct());
    }
    @PutMapping
    public ResponseEntity<?> updateStatusProducts(@RequestBody SensorProductRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.acceptProduct(request));
    }

}
