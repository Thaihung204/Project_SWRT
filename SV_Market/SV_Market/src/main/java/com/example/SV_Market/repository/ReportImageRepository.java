package com.example.SV_Market.repository;

import com.example.SV_Market.entity.ReportImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReportImageRepository extends JpaRepository<ReportImage, String> {
}
