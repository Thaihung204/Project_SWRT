package com.example.SV_Market.response;

import com.example.SV_Market.entity.Order;
import com.example.SV_Market.entity.Product;
import com.example.SV_Market.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportResponse {
    private String reportId;
    private String title;
    private String description;
    private String state;
    private String userName;
    private String userId;
    private String productName;  // Tên sản phẩm được lấy từ Product
    private String productId;
    private String responseMessage;
    private List<ReportImageRespone> images;
    private  String orderId;
     private Order order;


}

