package com.example.SV_Market.controller;


import com.example.SV_Market.request.PaymentRequest;
import com.example.SV_Market.request.TestRequest;
import com.example.SV_Market.request.TransactionRequest;
import com.example.SV_Market.service.CloudinaryService;
import com.example.SV_Market.service.VNPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
@CrossOrigin("*")
@RequestMapping("/payment")
public class VNPayController {
    @Autowired
    private VNPayService vnPayService;
    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping("/pay")
    public ResponseEntity<?> createPayment(@RequestBody PaymentRequest paymentRequest) throws UnsupportedEncodingException {
        return ResponseEntity.status(HttpStatus.OK).body(vnPayService.pay(paymentRequest));
    }

    @PostMapping("/call_back")
    public ResponseEntity<?> completeTransaction(@RequestBody TransactionRequest request) throws UnsupportedEncodingException {
        return ResponseEntity.status(HttpStatus.OK).body(vnPayService.transaction(request));
    }
//    @PutMapping
//    public String[] updateUser(@ModelAttribute TestRequest request) throws IOException {
//        return cloudinaryService.uploadProductImage(request.getImages());
//    }

}
