package com.example.SV_Market.controller;

import com.example.SV_Market.entity.Category;
import com.example.SV_Market.entity.Order;
import com.example.SV_Market.request.CategoryCreationRequest;
import com.example.SV_Market.request.OrderCreationRequest;
import com.example.SV_Market.response.OrderResponse;
import com.example.SV_Market.response.ProductResponse;
import com.example.SV_Market.service.CategoryService;
import com.example.SV_Market.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/receive")
    public ResponseEntity<List<OrderResponse>> getOrdersBySellerIdAndState(
            @RequestParam(value = "sell_id", required = false) String sellId,
            @RequestParam(value = "state", required = false) String state) {
        List<OrderResponse> orders = orderService.getOrderBySellerIdAndState(sellId,state);
        return new ResponseEntity<>(orders, HttpStatus.OK);
        }
            @GetMapping("/make")
        public ResponseEntity<List<OrderResponse>> getOrdersByBuyerIdAndState(
            @RequestParam(value = "buy_id", required = false) String buyId,
            @RequestParam(value = "state", required = false) String state) {
        List<OrderResponse> orders = orderService.getOrderByBuyerIdAndState(buyId,state);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping()
    public String updateOrder( @RequestParam(value = "orderId", required = false) String orderId,
                               @RequestParam(value = "state", required = false) String state){
        orderService.updateOrder(orderId,state);
        return "Order has been ${state} successful";
    }

    @PutMapping()
    public String confirmOrder(@RequestParam(value = "orderId", required = false) String orderId,
                               @RequestParam(value = "userId", required = false) String userId){
        orderService.confirmOrder(orderId,userId);
        return "Order has been confirm successful";
    }

    @DeleteMapping("/{orderId}")
    public String deleteOrder(@PathVariable String orderId){
    orderService.deleteOrder(orderId);
    return "Order has been deleted";
    }
}
