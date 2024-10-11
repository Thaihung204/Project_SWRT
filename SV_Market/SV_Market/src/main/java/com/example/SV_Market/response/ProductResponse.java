package com.example.SV_Market.response;

import com.example.SV_Market.entity.Category;
import com.example.SV_Market.entity.FeedBack;
import com.example.SV_Market.entity.ProductImage;
import com.example.SV_Market.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {

    private String productId;
    private UserResponse user;
    private String productName;
    private List<ProductImageResponse> images;
    private int quantity;
    private long price;
    private String description;
    private CategoryResponse category;
    private String type; // sell/exchange/se
    private String state; //new/used
    private String status; //doiduyet/daduyet-dangban/daduyet-an/fail
    private LocalDate create_at;
    private List<FeedbackResponse> feedBacks;
}
