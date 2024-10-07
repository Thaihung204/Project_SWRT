package com.example.SV_Market.repository;


import com.example.SV_Market.entity.BalanceFluctuation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<BalanceFluctuation,String> {
}
