package com.example.SV_Market.repository;

import com.example.SV_Market.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository  extends JpaRepository<Report, String> {
}
