package com.example.SV_Market.repository;

import com.example.SV_Market.entity.Upgrade;
import com.example.SV_Market.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpgradeRepository extends JpaRepository<Upgrade, String> {
    Upgrade findTopByUserOrderByStartDateDesc(User user);
}
