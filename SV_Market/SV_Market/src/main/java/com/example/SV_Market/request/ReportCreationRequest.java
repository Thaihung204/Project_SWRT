package com.example.SV_Market.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportCreationRequest {
    private String title;
    private String userId;
    private String productId;

    private String description;
    private String type;
    private String responseMessage;
}
