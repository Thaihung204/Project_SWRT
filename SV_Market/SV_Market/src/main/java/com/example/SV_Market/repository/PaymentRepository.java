package com.example.SV_Market.repository;


import com.example.SV_Market.entity.BalanceFluctuation;
import com.example.SV_Market.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<BalanceFluctuation,String> {
    @Query("SELECT u FROM BalanceFluctuation u WHERE u.user.userId = :userId")
    Optional<BalanceFluctuation> findByUserId(String userId);
}