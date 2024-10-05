package com.example.SV_Market.service;

import com.example.SV_Market.entity.Category;
import com.example.SV_Market.entity.Product;
import com.example.SV_Market.entity.ProductImage;
import com.example.SV_Market.repository.ProductRepository;
import com.example.SV_Market.request.ProductCreationRequest;
import com.example.SV_Market.request.ProductUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;
    public Product createProduct(ProductCreationRequest request){
        LocalDate currentDate = LocalDate.now();

        Product product = new Product();

        List<ProductImage> productImages = new ArrayList<>();  // Create an empty list to store the ProductImage objects

        for (String imagePath : request.getImages()) {  // Iterate over each image path from the request
            ProductImage productImage = new ProductImage();  // Create a new ProductImage object
            productImage.setPath(imagePath);  // Set the image path
            productImage.setProduct(product);  // Associate the image with the product
            productImages.add(productImage);  // Add the productImage to the list
        }


        product.setUser(userService.getUserById(request.getUserId()));
        product.setProductName(request.getProductName());
        product.setImages(productImages);
        product.setQuantity(request.getQuantity());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setCategory(categoryService.getCategory(request.getCategoryId()));
        product.setState(request.getState());
        product.setCreate_at(currentDate);
        product.setStatus(request.getStatus());

        return productRepository.save(product);
    }

    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product updateProduct(String productId, ProductUpdateRequest request){
        Product product = getProductById(productId);

        LocalDate currentDate = LocalDate.now();

        product.setProductName(request.getProductName());
        product.setQuantity(request.getQuantity());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setType((request.getType()));
        product.setState(request.getState());
        product.setStatus(request.getStatus());
        product.setCreate_at(currentDate);

        return productRepository.save(product);
    }



}
