package com.example.SV_Market.repository;

import com.example.SV_Market.entity.Category;
import com.example.SV_Market.entity.Product;
import com.example.SV_Market.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findById(String productId);

    @Query(value = "SELECT * FROM product WHERE userid = :userId AND status = :status", nativeQuery = true)
    List<Product> findProductsByUserIdAndStatus(@Param("userId") String userId, @Param("status") String status);


    //for admin to get product to kiem duyet
    @Query("SELECT u FROM Product u WHERE u.status = :status")
    List<Product> sensor(String status);
    @Query("SELECT p from Product p WHERE p.user.userId= :userId")
    List<Product> findAllByUserId(@Param("userId") String userId);

    //de hien thi tren trang home
    List<Product> findByStatus(String tatus);
    List<Product> findByCategory(Category category);

    @Query("SELECT e FROM Product e where e.category.categoryId = :categoryId")
    Page<Product> productListingByCategoryId(Pageable pageable, String categoryId);

    @Query("SELECT e FROM Product e")
    Page<Product> productListing(Pageable pageable);
    @Query("SELECT COUNT(p) FROM Product p WHERE MONTH(p.create_at) = :month AND YEAR(p.create_at) = :year")
    int countProductsUploadedByMonth(@Param("month") int month, @Param("year") int year);
}
