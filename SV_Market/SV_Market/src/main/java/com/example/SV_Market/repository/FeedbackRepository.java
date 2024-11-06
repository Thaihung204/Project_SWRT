package com.example.SV_Market.repository;

import com.example.SV_Market.entity.Category;
import com.example.SV_Market.entity.FeedBack;
import com.example.SV_Market.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedBack, String> {
    @Query(value = "SELECT COUNT(feedback_id) FROM feedback WHERE product_id = :productId AND sender_id = :senderId AND receiver_id = :receiverId", nativeQuery = true)
    int checkFeedbackExist(@Param("productId") String productId, @Param("senderId") String senderId , @Param("receiverId") String receiverId);

}
