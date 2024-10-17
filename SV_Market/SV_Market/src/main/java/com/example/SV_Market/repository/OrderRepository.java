package com.example.SV_Market.repository;

import com.example.SV_Market.entity.BalanceFluctuation;
import com.example.SV_Market.entity.Category;
import com.example.SV_Market.entity.Order;
import com.example.SV_Market.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    Optional<Order> findById(String orderId);

    @Query(value = "SELECT * FROM orders WHERE sell_id = :userId AND state = :state", nativeQuery = true)
    List<Order> findOrdersBySellerIdAndState(@Param("userId") String userId, @Param("state") String status);

    @Query(value = "SELECT * FROM orders WHERE buy_id = :userId AND state = :state", nativeQuery = true)
    List<Order> findOrdersByBuyerIdAndState(@Param("userId") String userId, @Param("state") String status);

}
