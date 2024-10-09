package com.example.SV_Market.request;

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
    private String reportId;
    private String title;
    private String description;
    private String type;
    private String responseMessage;
    private User user;
    private String productName;  // Tên sản phẩm được lấy từ Product


}

