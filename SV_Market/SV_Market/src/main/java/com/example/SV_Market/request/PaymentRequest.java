package com.example.SV_Market.request;

import lombok.Data;

@Data
public class PaymentRequest {
    private long amount;
    private String vnp_ReturnUrl;
}
