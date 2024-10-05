package com.example.SV_Market.request;

import com.example.SV_Market.entity.ProductImage;
import com.example.SV_Market.entity.User;
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
public class FeedbackCreationRequest {

    private String senderId;
    private String receiverId;
    private String productId;
    private int rating;
    private String description;

}
