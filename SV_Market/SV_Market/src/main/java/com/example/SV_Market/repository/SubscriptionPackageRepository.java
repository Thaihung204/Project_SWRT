package com.example.SV_Market.repository;


import com.example.SV_Market.entity.SubscriptionPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionPackageRepository extends JpaRepository<SubscriptionPackage, Long> {
}