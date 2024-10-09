package com.example.SV_Market.request;

import com.example.SV_Market.entity.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCreationRequest {
    protected String userId;
    protected String productName;
    protected MultipartFile[] images;
//    protected List<String> images;
    protected int quantity;
    protected long price;
    protected String description;
    protected String categoryId;
    protected String type;
    protected String state;
    private String status;

//        ProductCreationRequest a = ProductCreationRequest.builder()
//                .price(78).state("")..build();
}
