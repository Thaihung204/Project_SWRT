package com.example.SV_Market.service;

import com.example.SV_Market.entity.Category;
import com.example.SV_Market.entity.CategoryImage;
import com.example.SV_Market.entity.Product;
import com.example.SV_Market.repository.CategoryRepository;
import com.example.SV_Market.repository.ProductRepository;
import com.example.SV_Market.request.CategoryCreationRequest;
import com.example.SV_Market.request.CategoryUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    // Create Category with Images
    public Category createCategory(CategoryCreationRequest request) {
        Category category = new Category();

        // Upload images to cloud and create CategoryImage objects
        category.setTitle(request.getTitle());
        category.setDescription(request.getDescription());
        category.setImage(cloudinaryService.upload(request.getImage()));

        return categoryRepository.save(category);
    }

    // Update Category with New Images
    public Category updateCategory(String categoryId, CategoryUpdateRequest request) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category Not Found!"));

        // Upload new images and update the CategoryImage list
        category.setTitle(request.getTitle());
        category.setDescription(request.getDescription());
        if(request.getImage() != null){
            category.setImage(cloudinaryService.upload(request.getImage()));
        }
        return categoryRepository.save(category);
    }

    // Get all Categories
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    // Get a specific Category by ID
    public Category getCategory(String categoryId) {
        Optional<Category> findId = categoryRepository.findById(categoryId);
        if(findId.isPresent()){
            Category temp = findId.get();
            temp.setProducts(null);
            return temp;
        }
        return null;
//                .orElseThrow(() -> new RuntimeException("Category Not Found!"));
    }

    // Delete Category by ID
    public void deleteCategory(String categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));

        // Retrieve the existing "other" category
        Category otherCategory = categoryRepository.findByTitle("other").get();

        // Reassign all products to "other" category
        List<Product> products = productRepository.findByCategory(category);
        for (Product product : products) {
            product.setCategory(otherCategory);
        }
        productRepository.saveAll(products);

        // Delete the original category
        categoryRepository.delete(category);
    }
}
