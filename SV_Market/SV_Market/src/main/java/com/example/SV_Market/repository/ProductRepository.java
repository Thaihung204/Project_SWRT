package com.example.SV_Market.repository;

import com.example.SV_Market.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findById(String productId);
    @Query(value = "SELECT * FROM product WHERE userid = :userId AND status = 'public'", nativeQuery = true)
    List<Product> findPublicProductsByUserId(@Param("userId") String userId);

}
