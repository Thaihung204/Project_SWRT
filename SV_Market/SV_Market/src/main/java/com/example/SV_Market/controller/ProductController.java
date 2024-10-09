package com.example.SV_Market.controller;

import com.example.SV_Market.entity.Product;
import com.example.SV_Market.request.ProductCreationRequest;
import com.example.SV_Market.request.ProductUpdateRequest;
import com.example.SV_Market.response.ProductResponse;
import com.example.SV_Market.service.CloudinaryService;
import com.example.SV_Market.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping()
    Product createProduct(@RequestBody ProductCreationRequest request){
        return productService.createProduct(request);
    }

    // Get all products
    @GetMapping
    public List<ProductResponse> getProducts() {
        return productService.getProducts();
    }

    // Get a product by ID
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable String productId) {
        Product product = productService.getProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // Get public products by user ID (renamed endpoint to avoid conflict)
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Product>> getPublicProductsByUserId(@PathVariable String userId) {
        List<Product> products = productService.getPublicProductsByUserId(userId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Update a product
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable String productId, @RequestBody ProductUpdateRequest request) {
        Product updatedProduct = productService.updateProduct(productId, request);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
//    @PostMapping
//    public String[] uploadImages(@ModelAttribute ProductCreationRequest request) throws IOException {
//        return cloudinaryService.uploadProductImage(request.getImages());
//    }

}
