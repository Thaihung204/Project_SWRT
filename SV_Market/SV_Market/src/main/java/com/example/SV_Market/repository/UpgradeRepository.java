package com.example.SV_Market.repository;

import com.example.SV_Market.entity.Upgrade;
import com.example.SV_Market.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UpgradeRepository extends JpaRepository<Upgrade, Long> {

    @Query("SELECT p FROM Upgrade p WHERE p.user.userId = :userId ORDER BY p.upgradeId desc limit 1")
    Optional<Upgrade> findLatestByUserID(@Param("userId") String userId);

}
