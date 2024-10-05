package com.example.SV_Market.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryCreationRequest {
    private String categoryId;
    private String title;
    private String description;
    private String image;

}
