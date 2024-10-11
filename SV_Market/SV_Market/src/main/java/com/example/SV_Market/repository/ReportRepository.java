package com.example.SV_Market.repository;

import com.example.SV_Market.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportRepository  extends JpaRepository<Report, String> {
    List<Report> findByUser_UserId(String userId);
    @Query("SELECT r FROM Report r WHERE r.type = 'chưa giải quyết'")

    List<Report> findByType();
}
