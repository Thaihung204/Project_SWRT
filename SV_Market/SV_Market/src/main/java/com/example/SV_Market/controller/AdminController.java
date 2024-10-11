package com.example.SV_Market.controller;

import com.example.SV_Market.response.ReportResponse;
import com.example.SV_Market.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.SV_Market.request.SensorProductRequest;
import com.example.SV_Market.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController

@CrossOrigin("*")
//@RequestMapping("/reportadmins")
//public class AdminController {
//
//    private ReportService reportService;
//
//    @GetMapping()
//    public ResponseEntity<List<ReportResponse>> Getviewreports() {
//        List<ReportResponse> viewreports = reportService.viewReports();
//        return ResponseEntity.ok(viewreports);
//    }

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
