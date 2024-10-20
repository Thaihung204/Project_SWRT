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
public class ProductImageCreationRequest {
    private String productId;
    protected MultipartFile image;
}
