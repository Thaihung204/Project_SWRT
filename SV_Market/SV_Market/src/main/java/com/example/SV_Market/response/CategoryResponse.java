package com.example.SV_Market.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class CategoryResponse {
    private  String categoryId;
    private String title;
    private String description;
    private String image;
    public void setImage(String image) {
        this.image = image;
    }
}
