package com.example.SV_Market.repository;

import com.example.SV_Market.entity.Category;
import com.example.SV_Market.entity.Notice;
import com.example.SV_Market.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, String> {
    @Query(value = "SELECT * FROM notice WHERE userid = :userId", nativeQuery = true)
    List<Notice> getNoticeByUser(@Param("userId") String userId);
}
