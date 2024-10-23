package com.example.SV_Market.repository;

import com.example.SV_Market.entity.BalanceFluctuation;
import com.example.SV_Market.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceFluctuationRepository extends JpaRepository<BalanceFluctuation, String> {
    @Query("SELECT u FROM BalanceFluctuation u WHERE u.user.userId = :userId")
    List<BalanceFluctuation> findByUserId(String userId);
}
