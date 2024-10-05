package com.example.SV_Market.service;

import com.example.SV_Market.entity.Category;
import com.example.SV_Market.repository.CategoryRepository;
import com.example.SV_Market.request.CategoryCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Category createCategory(CategoryCreationRequest request){
        Category category = new Category();
        category.setTitle(request.getTitle());
        category.setDescription(request.getDescription());
        category.setImage(request.getImage());


        return categoryRepository.save(category);
    }

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategory(String categoryId){
        return categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category Not Found!"));
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
