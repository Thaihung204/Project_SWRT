package com.example.SV_Market.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionRequest {
    private String userId;
    private double vnp_Amount;
    private LocalDate vnp_PayDate;
    private String vnp_ResponseCode;
    private String vnp_OrderInfo;

}
