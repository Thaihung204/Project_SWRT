package com.example.SV_Market.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductUpdateRequest {

    protected String productName;
    protected int quantity;
    protected long price;
    protected String description;
    protected String categoryId;
    protected String type;
    protected String state;
    private String status;

}
