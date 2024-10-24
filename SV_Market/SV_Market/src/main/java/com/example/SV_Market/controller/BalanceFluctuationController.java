package com.example.SV_Market.controller;

import com.example.SV_Market.entity.BalanceFluctuation;
import com.example.SV_Market.request.OrderCreationRequest;
import com.example.SV_Market.response.OrderResponse;
import com.example.SV_Market.service.BalanceFluctuationService;
import com.example.SV_Market.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/fluctuation")
public class BalanceFluctuationController {
    @Autowired
    private BalanceFluctuationService balanceFluctuationService;

    @GetMapping("/{userId}")
    List<BalanceFluctuation> getFlustuationByUser(@PathVariable String userid){
        return balanceFluctuationService.getBalanceFluctustionByUser(userid);
    }

}
