package com.example.SV_Market.repository;

import com.example.SV_Market.entity.Product;
import com.example.SV_Market.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findById(String productId);

    @Query("SELECT u FROM Product u WHERE u.status = :status")
    Optional<Product> sensor(String status);
    List<Product> findByStatus(String tatus);


}
