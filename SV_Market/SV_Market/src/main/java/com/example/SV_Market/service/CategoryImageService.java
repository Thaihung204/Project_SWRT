package com.example.SV_Market.service;

import com.example.SV_Market.entity.Category;
import com.example.SV_Market.entity.CategoryImage;
import com.example.SV_Market.repository.CategoryImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryImageService {
    @Autowired
    private CategoryImageRepository categoryImageRepository;

    public List<CategoryImage> saveCategoryImages(Category category, List<String> imageUrls) {
        List<CategoryImage> categoryImages = new ArrayList<>();
        for (String url : imageUrls) {
            CategoryImage categoryImage = new CategoryImage();
            categoryImage.setCategory(category);
            categoryImage.setPath(url);
            categoryImages.add(categoryImage);
            categoryImageRepository.save(categoryImage);
        }
        return categoryImages;
    }
}
