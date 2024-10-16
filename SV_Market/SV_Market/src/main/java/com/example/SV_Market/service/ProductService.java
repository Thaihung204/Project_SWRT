package com.example.SV_Market.service;

import com.example.SV_Market.entity.Category;
import com.example.SV_Market.entity.Product;
import com.example.SV_Market.entity.ProductImage;
import com.example.SV_Market.entity.User;
import com.example.SV_Market.repository.ProductRepository;
import com.example.SV_Market.request.ProductCreationRequest;
import com.example.SV_Market.request.ProductUpdateRequest;
import com.example.SV_Market.response.*;
import com.example.SV_Market.request.SensorProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CloudinaryService cloudinaryService;
    public Product createProduct(ProductCreationRequest request){
        LocalDate currentDate = LocalDate.now();

        Product product = new Product();

        List<ProductImage> productImages = new ArrayList<>();  // Create an empty list to store the ProductImage objects

            for (String imagePath : cloudinaryService.uploadProductImage(request.getImages())) {  // Iterate over each image path from the request
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
        product.setType(request.getType());
        product.setStatus("pending");

        return productRepository.save(product);
    }

    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public List <ProductResponse> getPublicProduct(String status) {
        return  formatListProductResponse(productRepository.findByStatus(status));
    }

    public List<ProductResponse> getAllProducts(){
        return formatListProductResponse(productRepository.findAll());
    }

    public List<ProductResponse> getProducts() {
        return formatListProductResponse(productRepository.findAll());

    }
    public List<ProductResponse> getPublicProductsByUserId(String userId, String status) {
        return formatListProductResponse(productRepository.findProductsByUserIdAndStatus(userId, status));
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
    public Product updateProductStatus(String productId, String status){
        Product product = getProductById(productId);
        product.setStatus(status);
        return productRepository.save(product);
    }

    public List<Product> sensorProduct(){
        Optional<Product> list = productRepository.sensor("pending");
        if(list.isPresent()){
            return list.stream().toList();
        }
        return null;
    }

    public Product acceptProduct(SensorProductRequest request) {
        Product product = getProductById(request.getProductId());
        product.setStatus(request.getStatus());
        return productRepository.save(product);
    }

    public void deleteProduct(String productId){
        productRepository.deleteById(productId);
    }

    public ProductResponse formatProductResponse(Product product){

            ProductResponse response = new ProductResponse();
            response.setProductId(product.getProductId());
            response.setProductName(product.getProductName());

            User user = product.getUser();
            UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getUserId());
            userResponse.setUserName(user.getUserName());
            userResponse.setAddress(user.getAddress());
            userResponse.setProfilePicture(user.getProfilePicture());

            response.setUser(userResponse);

            List<ProductImageResponse> imageResponses = product.getImages().stream().map(image -> {
                ProductImageResponse imageResponse = new ProductImageResponse();
                imageResponse.setPath(image.getPath());
                return imageResponse;
            }).collect(Collectors.toList());

            response.setImages(imageResponses);
            response.setQuantity(product.getQuantity());
            response.setPrice(product.getPrice());
            response.setDescription(product.getDescription());

            Category category = product.getCategory();
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setTitle(category.getTitle());
            categoryResponse.setDescription(category.getDescription());
        if (!category.getImage().isEmpty()) {
            categoryResponse.setImage(category.getImage().get(0).getPath());
        }

            response.setCategory(categoryResponse);
            response.setType(product.getType());
            response.setState(product.getState());
            response.setCreate_at(product.getCreate_at());

            List<FeedbackResponse> feedbackResponses = product.getFeedBacks().stream().map(feedback -> {
                FeedbackResponse feedbackResponse = new FeedbackResponse();

                User fuser = feedback.getSender();
                UserResponse fuserResponse = new UserResponse();
                fuserResponse.setUserName(fuser.getUserName());
                fuserResponse.setProfilePicture(fuser.getProfilePicture());
                feedbackResponse.setSender(fuserResponse);
                feedbackResponse.setRating(feedback.getRating());
                feedbackResponse.setDescription(feedback.getDescription());
                feedbackResponse.setCreatedAt(feedback.getCreatedAt());
                
                return feedbackResponse;
            }).collect(Collectors.toList());
            response.setFeedBacks(feedbackResponses);

            return response;

    }

    public List<ProductResponse> formatListProductResponse(List<Product> products){
        return products.stream().map(product -> {
            ProductResponse response = new ProductResponse();
            response.setProductId(product.getProductId());
            response.setProductName(product.getProductName());

            User user = product.getUser();
            UserResponse userResponse = new UserResponse();
            userResponse.setUserName(user.getUserName());
            userResponse.setAddress(user.getAddress());
            userResponse.setProfilePicture(user.getProfilePicture());

            response.setUser(userResponse);

            List<ProductImageResponse> imageResponses = product.getImages().stream().map(image -> {
                ProductImageResponse imageResponse = new ProductImageResponse();
                imageResponse.setPath(image.getPath());
                return imageResponse;
            }).collect(Collectors.toList());

            response.setImages(imageResponses);
            response.setQuantity(product.getQuantity());
            response.setPrice(product.getPrice());
            response.setDescription(product.getDescription());

//            Category category = product.getCategory();
//            CategoryResponse categoryResponse = new CategoryResponse();
//            categoryResponse.setTitle(category.getTitle());
//            categoryResponse.setDescription(category.getDescription());
//            categoryResponse.setImage(category.getImage());
//            response.setCategory(categoryResponse);

//            response.setType(product.getType());
//            response.setState(product.getState());
            response.setCreate_at(product.getCreate_at());

//            List<FeedbackResponse> feedbackResponses = product.getFeedBacks().stream().map(feedback -> {
//                FeedbackResponse feedbackResponse = new FeedbackResponse();
//
//                User fuser = feedback.getSender();
//                UserResponse fuserResponse = new UserResponse();
//                fuserResponse.setUserName(fuser.getUserName());
//                fuserResponse.setProfilePicture(fuser.getProfilePicture());
//                feedbackResponse.setSender(fuserResponse);
//                feedbackResponse.setRating(feedback.getRating());
//                feedbackResponse.setDescription(feedback.getDescription());
//                feedbackResponse.setCreatedAt(feedback.getCreatedAt());
//
//                return feedbackResponse;
//            }).collect(Collectors.toList());
//            response.setFeedBacks(feedbackResponses);

            return response;
        }).collect(Collectors.toList());
    }


}
