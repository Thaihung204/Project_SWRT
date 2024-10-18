package com.example.SV_Market.service;

import com.example.SV_Market.entity.Product;
import com.example.SV_Market.entity.ProductImage;
import com.example.SV_Market.repository.ProductImageRepository;
import com.example.SV_Market.request.ProductImageCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductImageService {
    @Autowired
    ProductImageRepository productImageRepository;
    @Autowired
    CloudinaryService cloudinaryService;
    @Autowired
    ProductService productService;

    public ProductImage createProductImage(ProductImageCreationRequest request){
        String imagePath = cloudinaryService.upload(request.getImage());  // Iterate over each image path from the request
            ProductImage productImage = new ProductImage();  // Create a new ProductImage object
            productImage.setPath(imagePath);  // Set the image path
            productImage.setProduct(productService.getProductById(request.getProductId()));  // Associate the image with the product

        return productImageRepository.save(productImage);
    }

    public List<ProductImage> getImages(String productId) {
        return (List<ProductImage>) productImageRepository.findById(productId).orElseThrow(() -> new RuntimeException("ProductImage Not Found!"));
    }

    public void deleteProductImage(String imageId){
        productImageRepository.deleteById(imageId);
    }

}
