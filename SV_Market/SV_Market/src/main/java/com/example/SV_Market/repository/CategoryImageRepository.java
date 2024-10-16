package com.example.SV_Market.repository;

import com.example.SV_Market.entity.CategoryImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryImageRepository extends JpaRepository<CategoryImage, String> {

}