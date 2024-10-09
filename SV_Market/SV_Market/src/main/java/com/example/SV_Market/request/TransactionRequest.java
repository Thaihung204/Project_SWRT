package com.example.SV_Market.request;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class TransactionRequest {
    private String userId;
    private double vnp_Amount;
    private String vnp_PayDate;
    private String vnp_ResponseCode;
    private String vnp_OrderInfo;
    private LocalDate date;



    public void setDate() {
        String year = vnp_PayDate.substring(0,4);
        year +="/" + vnp_PayDate.substring(4,6);
        year +="/" + vnp_PayDate.substring(6,8);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate a = LocalDate.parse(year,dateTimeFormatter);
        this.date=a;

    }
}
