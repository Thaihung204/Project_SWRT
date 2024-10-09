package com.example.SV_Market.repository;

import com.example.SV_Market.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<Category, String> {
}
