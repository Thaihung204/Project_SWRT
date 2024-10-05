package com.example.SV_Market.request;

import com.example.SV_Market.entity.ProductImage;

import java.util.List;

public class ProductImageCreationRequest {
    private List<ProductImage> images;
    private String productId;
    private String path;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
