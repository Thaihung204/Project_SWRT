package com.example.SV_Market.controller;

import com.example.SV_Market.entity.Product;
import com.example.SV_Market.entity.ProductImage;
import com.example.SV_Market.request.ProductCreationRequest;
import com.example.SV_Market.request.ProductImageCreationRequest;
import com.example.SV_Market.response.ProductResponse;
import com.example.SV_Market.service.CloudinaryService;
import com.example.SV_Market.service.ProductImageService;
import com.example.SV_Market.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/image")
public class ProductImageController {
    @Autowired
    private ProductImageService productImageService;
    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping()
    ProductImage createProductImage(@ModelAttribute ProductImageCreationRequest request){
        return productImageService.createProductImage(request);
    }


    @DeleteMapping("/{imageId}")
    public String deleteProduct(@PathVariable String imageId){
        productImageService.deleteProductImage(imageId);
        return "Product has been deleted";
    }

}
