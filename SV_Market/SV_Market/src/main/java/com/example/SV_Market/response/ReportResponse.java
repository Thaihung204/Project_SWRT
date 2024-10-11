package com.example.SV_Market.response;

import com.example.SV_Market.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportResponse {
    private String title;
    private String description;
    private String state;
    private String userName;
    private String productName;  // Tên sản phẩm được lấy từ Product
    private String responseMessage;




}

