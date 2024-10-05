package com.example.SV_Market.service;

import com.example.SV_Market.entity.Product;
import com.example.SV_Market.entity.ProductImage;
import com.example.SV_Market.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductImageService {
    @Autowired
    ProductImageRepository productImageRepository;

    public List<ProductImage> createProductImage(Product product, List<String> images){
        List<ProductImage> imagesReturn = new ArrayList<>();
        for(String image : images) {
            ProductImage productImage = new ProductImage();
            productImage.setProduct(product);
            productImage.setPath(image);
            imagesReturn.add(productImage);
            productImageRepository.save(productImage);
        }
        return imagesReturn;
    }

    public List<ProductImage> getImages(String productId) {
        return (List<ProductImage>) productImageRepository.findById(productId).orElseThrow(() -> new RuntimeException("ProductImage Not Found!"));
    }



//    public Product updateProduct(String productId, ProductUpdateRequest request){
//        Product product = getProduct(productId);
//
//        LocalDate currentDate = LocalDate.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        String formattedDate = currentDate.format(formatter);
//
//        product.setProductName(request.getProductName());
//        product.setQuantity(request.getQuantity());
//        product.setPrice(request.getPrice());
//        product.setDescription(request.getDescription());
//        product.setCategoryId(request.getCategoryId());
//        product.setState(request.getState());
//        product.setCreate_at(currentDate);
//
//        return productRepository.save(product);
//    }
}
