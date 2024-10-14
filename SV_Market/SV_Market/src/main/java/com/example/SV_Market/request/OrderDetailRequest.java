package com.example.SV_Market.request;

import com.example.SV_Market.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailRequest {

    private String productId;
    private int quantity;
    private String productTradeId;
}
