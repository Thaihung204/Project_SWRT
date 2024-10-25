package com.example.SV_Market.service;

import com.example.SV_Market.entity.*;
import com.example.SV_Market.repository.UserRepository;
import com.example.SV_Market.repository.CategoryRepository;
import com.example.SV_Market.repository.ProductRepository;
import com.example.SV_Market.repository.UpgradeRepository;
import com.example.SV_Market.request.ProductCreationRequest;
import com.example.SV_Market.request.ProductUpdateRequest;
import com.example.SV_Market.response.*;
import com.example.SV_Market.request.SensorProductRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class ProductService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CloudinaryService cloudinaryService;


    public Product createProduct(ProductCreationRequest request){

        LocalDate currentDate = LocalDate.now();

        Product product = new Product();
        User currentUpgrade = userRepository.findById(request.getUserId()).get();

        // Kiểm tra giới hạn sản phẩm
        int availableLimit = getProductCreationLimit(currentUpgrade);

        if (availableLimit <= 0) {
            return product; //thong bao khong the tao san pham
        }



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

    public List<ProductResponse> getPublicProductsByUserId(String userId, String status) throws IOException {
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

    public List<ProductResponse> sensorProduct(){
        List<Product> list = productRepository.sensor("pending");
        return formatListProductResponse(list);
    }

    public Product acceptProduct(SensorProductRequest request) {
        Product product = getProductById(request.getProductId());
        product.setStatus(request.getStatus());
        return productRepository.save(product);
    }

    //hung viet de giam sl sp
    public void decreaseProductQuan(String productId,int quan){
        Product product = getProductById(productId);
        if(product.getQuantity()<quan)
            throw new RuntimeException("Product do not enought!");
        else if (product.getQuantity()==quan) {
            product.setQuantity(0);
            product.setStatus("hide");
            productRepository.save(product);
        }
        else {
            int newQuan = product.getQuantity()-quan;
            product.setQuantity(newQuan);
            productRepository.save(product);
        }

    }

    public Page<ProductResponse> getProductListing(
            int page,String sortType, String categoryId, String address, String productName, Double minPrice, Double maxPrice) {
        Sort sortOrder = Sort.unsorted();  // Giá trị mặc định là không sắp xếp.
        if ("desc".equalsIgnoreCase(sortType)) {
            sortOrder = Sort.by("price").descending();
        } else {
            sortOrder = Sort.by("price").ascending();
        }

        Pageable pageable = PageRequest.of(page -1, 30);
        Stream<Product> productsStream = productRepository.findAll().stream();

        if (categoryId != null) {
            productsStream = productsStream
                    .filter(product -> product.getCategory().getCategoryId().equals(categoryId));
        }
        if (address != null) {
            productsStream = productsStream
                    .filter(product -> product.getUser().getAddress().contains(address));
        }
        if (minPrice != null) {
            productsStream = productsStream
                    .filter(product -> product.getPrice() >= minPrice);
        }
        if (maxPrice != null) {
            productsStream = productsStream
                    .filter(product -> product.getPrice() <= maxPrice);
        }
        if (productName != null) {
            productsStream = productsStream
                    .filter(product -> product.getProductName().contains(productName));
        }
        List<Product> filteredProducts = productsStream
                .filter(product -> product.getStatus().equals("public"))
                .collect(Collectors.toList());

        if (sortOrder.isSorted()) {
            Comparator<Product> comparator = Comparator.comparing(Product::getPrice);
            if (sortOrder.getOrderFor("price").isDescending()) {
                comparator = comparator.reversed();
            }
            filteredProducts.sort(comparator);
        }

        long totalElements = filteredProducts.size();
//        log.info("Total elements = " + totalElements);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), filteredProducts.size());
//        log.info("start = " + start + ", end = " + end);
        List<ProductResponse> pageProducts = formatListProductResponse(filteredProducts.subList(start, end));

//        log.info("list pro: " + pageProducts.size());
//
        return new PageImpl<>(pageProducts, pageable, totalElements);
    }

    public ResponseEntity<?> updateProductStatus(String productId, String status) {
        try {
            Product product = getProductById(productId);
            if (product.getQuantity() == 0 && status.equals("public")) {
                throw new RuntimeException("Cannot publish a product with zero quantity!");
            }
            product.setStatus(status);
            Product updatedProduct = productRepository.save(product);
            return ResponseEntity.ok(updatedProduct);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
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
            categoryResponse.setImage(category.getImage());

            response.setCategory(categoryResponse);
            response.setType(product.getType());
            response.setState(product.getState());
            response.setCreate_at(product.getCreate_at());

            List<FeedbackResponse> feedbackResponses = product.getFeedBacks().stream().map(feedback -> {
                FeedbackResponse feedbackResponse = new FeedbackResponse();

                User fuser = feedback.getSender();
                UserResponse fuserResponse = new UserResponse();
                fuserResponse.setUserId(fuser.getUserId());
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

    public ProductResponse formatProductOrderResponse(Product product){

            ProductResponse response = new ProductResponse();
            response.setProductId(product.getProductId());
            response.setProductName(product.getProductName());

            List<ProductImageResponse> imageResponses = product.getImages().stream().map(image -> {
                ProductImageResponse imageResponse = new ProductImageResponse();
                imageResponse.setPath(image.getPath());
                return imageResponse;
            }).collect(Collectors.toList());
            response.setImages(imageResponses);
            response.setQuantity(product.getQuantity());
            response.setPrice(product.getPrice());
            response.setDescription(product.getDescription());
            response.setCreate_at(product.getCreate_at());
            return response;

    }

    private int getProductCreationLimit(User currentUpgrade) {
        int publicProductCount = productRepository.findProductsByUserIdAndStatus(currentUpgrade.getUserId(), "public").size();
        int pendingProductCount = productRepository.findProductsByUserIdAndStatus(currentUpgrade.getUserId(), "pending").size();
        int hiddenProductCount = productRepository.findProductsByUserIdAndStatus(currentUpgrade.getUserId(), "hide").size();

        int totalAvailableProducts = publicProductCount + pendingProductCount + hiddenProductCount;
        String packageType = currentUpgrade.getRole(); // Assuming the type corresponds to the package name
        switch (packageType) {
            case "business":
                return 40 - totalAvailableProducts; // Limit for business package
            case "sub-business":
                return 15 - totalAvailableProducts; // Limit for sub-business package

            default:
                return 5 - totalAvailableProducts;  // Limit for standard package
        }
    }
}
