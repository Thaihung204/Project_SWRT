package com.example.SV_Market.service;

import com.example.SV_Market.entity.Category;
import com.example.SV_Market.entity.Order;
import com.example.SV_Market.entity.OrderDetail;
import com.example.SV_Market.entity.Product;
import com.example.SV_Market.repository.CategoryRepository;
import com.example.SV_Market.repository.OrderRepository;
import com.example.SV_Market.request.CategoryCreationRequest;
import com.example.SV_Market.request.OrderCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService ;

    public Order createOrder(OrderCreationRequest request){
        Order order = new Order();
        LocalDate currentDate = LocalDate.now();
        List<OrderDetail> orderDetails =
        request.getOrderDetails().stream().map(o -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProduct(productService.getProductById(o.getProductId()));
            if(request.getType().equals("exchange"))
                orderDetail.setProductTrade(productService.getProductById(o.getProductTradeId()));

            orderDetail.setQuantity(o.getQuantity());
            orderDetail.setOrder(order);
            return orderDetail;
        }).collect(Collectors.toList());
        order.setOrderDetails(orderDetails);
        order.setSeller(userService.getUserById(request.getSellerId()));
        order.setBuyer(userService.getUserById(request.getBuyerId()));
        order.setType(request.getType());
        order.setState(request.getState());
        order.setPaymentBy(request.getPaymentBy());
        order.setCreateAt(currentDate);

        return orderRepository.save(order);
    }

    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }

    public Order getOrderById(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

//    public Product updateProduct(String productId, ProductUpdateRequest request){
//        Product product = getProduct(productId);
//
//        LocalDate currentDate = LocalDate.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        String formattedDate = currentDate.format(formatter);
//
//        product.setProductName(request.getProductName());
//        product.setQuantity(request.getQuantity());
//        product.setPrice(request.getPrice());
//        product.setDescription(request.getDescription());
//        product.setCategoryId(request.getCategoryId());
//        product.setState(request.getState());
//        product.setCreate_at(currentDate);
//
//        return productRepository.save(product);
//    }
}
