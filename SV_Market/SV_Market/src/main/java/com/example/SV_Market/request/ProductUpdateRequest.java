package com.example.SV_Market.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductUpdateRequest {

    private String productName;
    private int quantity;
    private long price;
    private String description;
    private String type;
    private String state;
    private String status;

}
