package com.example.SV_Market.controller;

import com.example.SV_Market.entity.Category;
import com.example.SV_Market.request.CategoryCreationRequest;
import com.example.SV_Market.request.CategoryUpdateRequest;
import com.example.SV_Market.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @PostMapping()
    public Category createCategory(@ModelAttribute("request") CategoryCreationRequest request,
                                   @ModelAttribute("images") MultipartFile[] images) {
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

    @PutMapping("/{categoryId}")
    public Category updateCategory(@PathVariable String categoryId,
                                   @ModelAttribute("request") CategoryUpdateRequest request,
                                   @ModelAttribute("images") MultipartFile[] newImages) {
        return categoryService.updateCategory(categoryId, request);
    }

    @DeleteMapping("/{categoryId}")
    public String deleteCategory(@PathVariable String categoryId) {
        categoryService.deleteCategory(categoryId);
        return "Category has been deleted";
    }
}
//
//    @PutMapping("/{productId}")
//    Product updateProduct(@PathVariable String productId, @RequestBody ProductUpdateRequest request) {
//        return productService.updateProduct(productId, request);
//    }

