package com.example.SV_Market.service;

import com.example.SV_Market.repository.UserRepository;
import com.example.SV_Market.repository.ProductRepository;
import com.example.SV_Market.repository.BalanceFluctuationRepository;
import com.example.SV_Market.repository.UpgradeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

    @Service
    public class StatisticsService {

        private final UserRepository userRepository;
        private final ProductRepository productRepository;
        private final BalanceFluctuationRepository balanceFluctuationRepository;


        public StatisticsService(UserRepository userRepository, ProductRepository productRepository,
                                 BalanceFluctuationRepository balanceFluctuationRepository) {
            this.userRepository = userRepository;
            this.productRepository = productRepository;
            this.balanceFluctuationRepository = balanceFluctuationRepository;
        }

        // Lấy thống kê cho một tháng cụ thể
        public Map<String, Object> getMonthlyStatistics(int month, int year) {
            Map<String, Object> statistics = new HashMap<>();

            // Số người đăng ký mới
            int newRegistrations = userRepository.countNewRegistrationsByMonth(month, year);
            statistics.put("newRegistrations", newRegistrations);

            // Số sản phẩm đăng lên
            int productUploads = productRepository.countProductsUploadedByMonth(month, year);
            statistics.put("productUploads", productUploads);

            // Số giao dịch hoàn thành
            Integer completedTransactions = balanceFluctuationRepository.countCompletedTransactionsByMonth(month, year);
            statistics.put("completedTransactions", completedTransactions);

            // Tổng số tiền giao dịch
            Double totalTransactionAmount = balanceFluctuationRepository.sumTransactionAmountByMonth(month, year);
            statistics.put("totalTransactionAmount", (totalTransactionAmount != null) ? totalTransactionAmount : 0.0);

            // Doanh thu từ bán gói
            Double upgradeSales = balanceFluctuationRepository.sumAmountByUpgradeAndDate("Upgrade", month, year);
            statistics.put("upgradeSales", (upgradeSales != null) ? upgradeSales : 0.0);

            return statistics;
        }
    }


