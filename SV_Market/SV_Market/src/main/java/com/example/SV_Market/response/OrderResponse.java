package com.example.SV_Market.response;

import com.example.SV_Market.entity.OrderDetail;
import com.example.SV_Market.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)

public class OrderResponse {

    private String orderId;
    private List<OrderDetailResponse> orderDetails;
    private String type;
    private UserResponse seller;
    private UserResponse buyer;
    private String paymentBy;
    private String state;
    private LocalDate createAt;
    private String total;
    private String confirm;
}
