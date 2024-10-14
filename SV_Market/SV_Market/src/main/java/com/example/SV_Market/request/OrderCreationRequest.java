package com.example.SV_Market.request;

import com.example.SV_Market.entity.OrderDetail;
import com.example.SV_Market.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreationRequest {

    private List<OrderDetailRequest> orderDetails; // include productId,quan,productTradeId
    private String type;
    private String sellerId;
    private String buyerId;
    private String paymentBy;
    private String state;
}
