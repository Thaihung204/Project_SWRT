package com.example.SV_Market.controller;

import com.example.SV_Market.entity.Product;
import com.example.SV_Market.request.ProductCreationRequest;
import com.example.SV_Market.request.ProductUpdateRequest;
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

    Product createProduct(@ModelAttribute  ProductCreationRequest request){

        return productService.createProduct(request);
    }

//    @GetMapping()
//    List<Product> getProducts(){
//        return productService.getAllProducts();
//    }
    @GetMapping()
    List<Product> getPublicProduct(){
        return productService.getPublicProduct("public");
    }


    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable String productId) {
        Product product = productService.getProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @PutMapping("/{productId}")
    Product updateProduct(@PathVariable String productId, @RequestBody ProductUpdateRequest request) {
        return productService.updateProduct(productId, request);
    }

//    @PostMapping
//    public String[] createProduct2(@ModelAttribute ProductCreationRequest request) throws IOException {
//        return cloudinaryService.uploadProductImage(request.getImages());
//    }
}
