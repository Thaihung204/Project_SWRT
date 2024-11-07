package com.example.SV_Market.repository;

import com.example.SV_Market.entity.BalanceFluctuation;
import com.example.SV_Market.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceFluctuationRepository extends JpaRepository<BalanceFluctuation, String> {
    @Query("SELECT u FROM BalanceFluctuation u WHERE u.user.userId = :userId")
    List<BalanceFluctuation> findByUserId(String userId);

    // Đếm số giao dịch hoàn thành trong một tháng
    @Query("SELECT COUNT(b) FROM BalanceFluctuation b WHERE b.state = 'Giao dịch thành công' AND MONTH(b.date) = :month AND YEAR(b.date) = :year")
    Integer countCompletedTransactionsByMonth(@Param("month") int month, @Param("year") int year);

    // Tính tổng số tiền giao dịch trong một tháng
    @Query("SELECT SUM(b.amount) FROM BalanceFluctuation b WHERE b.state = 'Giao dịch thành công' AND MONTH(b.date) = :month AND YEAR(b.date) = :year")
    Double sumTransactionAmountByMonth(@Param("month") int month, @Param("year") int year);
    @Query("SELECT SUM(b.amount) FROM BalanceFluctuation b " +
            "WHERE b.content LIKE %:upgradeKeyword% " +
            "AND b.state = 'Giao dịch thành công' " +
            "AND MONTH(b.date) = :month AND YEAR(b.date) = :year")
    Double sumAmountByUpgradeAndDate(@Param("upgradeKeyword") String upgradeKeyword,
                                     @Param("month") int month,
                                     @Param("year") int year);

}
