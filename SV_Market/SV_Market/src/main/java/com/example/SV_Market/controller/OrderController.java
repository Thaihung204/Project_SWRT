package com.example.SV_Market.controller;

import com.example.SV_Market.entity.Category;
import com.example.SV_Market.entity.Order;
import com.example.SV_Market.request.CategoryCreationRequest;
import com.example.SV_Market.request.OrderCreationRequest;
import com.example.SV_Market.response.OrderResponse;
import com.example.SV_Market.service.CategoryService;
import com.example.SV_Market.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping()
    String createOrder(@RequestBody List<OrderCreationRequest> requests){
        return orderService.createOrder(requests);
    }

    @GetMapping()
    List<OrderResponse> getAllOrder(){
        return orderService.getAllOrder();
    }

    @GetMapping("/{orderId}")
    OrderResponse getOrder(@PathVariable String orderId){
        return orderService.formatOrder(orderService.getOrderById(orderId));
    }

//    @PutMapping("/{productId}")
//    Product updateProduct(@PathVariable String productId, @RequestBody ProductUpdateRequest request) {
//        return productService.updateProduct(productId, request);
//    }
}
