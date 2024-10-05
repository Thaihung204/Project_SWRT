package com.example.SV_Market.controller;

import com.example.SV_Market.entity.Category;
import com.example.SV_Market.request.CategoryCreationRequest;
import com.example.SV_Market.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping()
    Category createCategory(@RequestBody CategoryCreationRequest request){
        return categoryService.createCategory(request);
    }

    @GetMapping()
    List<Category> getCategories(){
        return categoryService.getCategories();
    }

    @GetMapping("/{categoryId}")
    Category getCategory(@PathVariable String categoryId){
        return categoryService.getCategory(categoryId);
    }
//
//    @PutMapping("/{productId}")
//    Product updateProduct(@PathVariable String productId, @RequestBody ProductUpdateRequest request) {
//        return productService.updateProduct(productId, request);
//    }
}
