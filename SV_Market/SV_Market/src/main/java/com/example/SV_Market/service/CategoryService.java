package com.example.SV_Market.service;

import com.example.SV_Market.entity.Category;
import com.example.SV_Market.entity.CategoryImage;
import com.example.SV_Market.repository.CategoryRepository;
import com.example.SV_Market.request.CategoryCreationRequest;
import com.example.SV_Market.request.CategoryUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    // Create Category with Images
    public Category createCategory(CategoryCreationRequest request, MultipartFile[] images) {
        Category category = new Category();

        // Upload images to cloud and create CategoryImage objects
        List<CategoryImage> categoryImages = new ArrayList<>();
        for (String imagePath : cloudinaryService.uploadProductImage(images)) {
            CategoryImage categoryImage = new CategoryImage();
            categoryImage.setPath(imagePath);
            categoryImage.setCategory(category);
            categoryImages.add(categoryImage);
        }

        category.setTitle(request.getTitle());
        category.setDescription(request.getDescription());
        category.setImage(categoryImages); // Set images

        return categoryRepository.save(category);
    }

    // Update Category with New Images
    public Category updateCategory(String categoryId, CategoryUpdateRequest request, MultipartFile[] newImages) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category Not Found!"));

        // Upload new images and update the CategoryImage list
        List<CategoryImage> categoryImages = new ArrayList<>();
        for (String imagePath : cloudinaryService.uploadProductImage(newImages)) {
            CategoryImage categoryImage = new CategoryImage();
            categoryImage.setPath(imagePath);
            categoryImage.setCategory(category); // Associate image with category
            categoryImages.add(categoryImage);
        }

        category.setTitle(request.getTitle());
        category.setDescription(request.getDescription());
        category.setImage(categoryImages); // Update images

        return categoryRepository.save(category);
    }

    // Get all Categories
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    // Get a specific Category by ID
    public Category getCategory(String categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category Not Found!"));
    }

    // Delete Category by ID
    public void deleteCategory(String categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));

        // Xóa Category và tất cả các Product liên quan nhờ cascade và orphanRemoval
        categoryRepository.delete(category);
    }
}
