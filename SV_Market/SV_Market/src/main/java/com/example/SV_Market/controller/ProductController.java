package com.example.SV_Market.controller;

import com.example.SV_Market.entity.Product;
import com.example.SV_Market.request.ProductCreationRequest;
import com.example.SV_Market.response.ProductResponse;
import com.example.SV_Market.service.CloudinaryService;
import com.example.SV_Market.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    // Get all products
//    @GetMapping
//    public List<ProductResponse> getProducts() {
//        return productService.getProducts();

//    @GetMapping()
//    List<Product> getProducts(){
//        return productService.getAllProducts();
//    }
    @GetMapping()
    List<ProductResponse> getPublicProduct(){
        return productService.getPublicProduct("public");
    }

    // Get a product by ID
    @GetMapping("/{productId}")
    public ProductResponse getProductById(@PathVariable String productId) {
        Product product = productService.getProductById(productId);
        return productService.formatProductResponse(product);
    }

    // Get public products by user ID
    @GetMapping("/shop")
    public ResponseEntity<List<ProductResponse>> getProductsByUserIdAndStatus(
            @RequestParam(value = "userId", required = false) String userId,
            @RequestParam(value = "status", required = false) String status) {
        List<ProductResponse> products = productService.getPublicProductsByUserId(userId,status);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Update a product
//    @PutMapping("/update/{productId}")
//    public ResponseEntity<Product> updateProduct(@PathVariable String productId, @RequestBody ProductUpdateRequest request) {
//        Product updatedProduct = productService.updateProduct(productId, request);
//        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
//    }

    @PutMapping("/update")
    public void updateProductStatus(
            @RequestParam(value = "productId", required = false) String productId,
            @RequestParam(value = "status", required = false) String status
    ) {
        productService.updateProductStatus(productId, status);
    }
//    @PostMapping
//    public String[] uploadImages(@ModelAttribute ProductCreationRequest request) throws IOException {
//        return cloudinaryService.uploadProductImage(request.getImages());
//    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable String productId){
        productService.deleteProduct(productId);
        return "Product has been deleted";
    }

}
