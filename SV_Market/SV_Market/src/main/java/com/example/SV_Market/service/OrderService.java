package com.example.SV_Market.service;

import com.example.SV_Market.entity.Category;
import com.example.SV_Market.entity.Order;
import com.example.SV_Market.entity.OrderDetail;
import com.example.SV_Market.entity.Product;
import com.example.SV_Market.repository.CategoryRepository;
import com.example.SV_Market.repository.OrderRepository;
import com.example.SV_Market.request.CategoryCreationRequest;
import com.example.SV_Market.request.OrderCreationRequest;
import com.example.SV_Market.response.OrderDetailResponse;
import com.example.SV_Market.response.OrderResponse;
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

    public String createOrder(List<OrderCreationRequest> requests){

        LocalDate currentDate = LocalDate.now();

        requests.stream().map(request -> {
            Order order = new Order();
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
            order.setState("pending");
            order.setTotal(request.getTotal());
            order.setPaymentBy(request.getPaymentBy());
            order.setCreateAt(currentDate);

            return orderRepository.save(order);
        }).collect(Collectors.toList());
    return "Order has been created";
    }

    public List<OrderResponse> getAllOrder(){
        return formatListOrder(orderRepository.findAll());
    }

    public Order getOrderById(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public List<OrderResponse> getOrderBySellerIdAndState(String sellId, String state){
        return formatListOrder(orderRepository.findOrdersBySellerIdAndState(sellId,state));
    }
    public List<OrderResponse> getOrderByBuyerIdAndState(String buyId, String state){
        return formatListOrder(orderRepository.findOrdersByBuyerIdAndState(buyId,state));
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

    public List<OrderResponse> formatListOrder(List<Order> orders){
        return  orders.stream().map(order -> {
            OrderResponse response = new OrderResponse();
            response.setOrderId(order.getOrderId());

            List<OrderDetailResponse> orderDetailResponses= order.getOrderDetails().stream().map(orderDetail -> {
                OrderDetailResponse orderDetailResponse = new OrderDetailResponse();
                if(orderDetail.getProduct()!=null)
                orderDetailResponse.setProduct(productService.formatProductOrderResponse(orderDetail.getProduct()));
                if(orderDetail.getProductTrade()!=null)
                orderDetailResponse.setProductTrade(productService.formatProductOrderResponse(orderDetail.getProductTrade()));
                orderDetailResponse.setQuantity(orderDetail.getQuantity());
                return orderDetailResponse;
            }).collect(Collectors.toList());
            response.setOrderDetails(orderDetailResponses);
            response.setSeller(userService.formatUser(order.getSeller()));
            response.setBuyer(userService.formatUser(order.getBuyer()));
            response.setType(order.getType());
            response.setState(order.getState());
            response.setPaymentBy(order.getPaymentBy());
            response.setCreateAt(order.getCreateAt());
            response.setTotal(order.getTotal());
            return response;
        }).collect(Collectors.toList());
    }

    public OrderResponse formatOrder(Order order){

            OrderResponse response = new OrderResponse();
            response.setOrderId(order.getOrderId());

            List<OrderDetailResponse> orderDetailResponses= order.getOrderDetails().stream().map(orderDetail -> {
                OrderDetailResponse orderDetailResponse = new OrderDetailResponse();
                if(orderDetail.getProduct()!=null)
                    orderDetailResponse.setProduct(productService.formatProductOrderResponse(orderDetail.getProduct()));
                if(orderDetail.getProductTrade()!=null)
                    orderDetailResponse.setProductTrade(productService.formatProductOrderResponse(orderDetail.getProductTrade()));
                orderDetailResponse.setQuantity(orderDetail.getQuantity());
                return orderDetailResponse;
            }).collect(Collectors.toList());
            response.setOrderDetails(orderDetailResponses);
            response.setSeller(userService.formatUser(order.getSeller()));
            response.setBuyer(userService.formatUser(order.getBuyer()));
            response.setType(order.getType());
            response.setState(order.getState());
            response.setPaymentBy(order.getPaymentBy());
            response.setCreateAt(order.getCreateAt());
            response.setTotal(order.getTotal());
            return response;

    }
}
