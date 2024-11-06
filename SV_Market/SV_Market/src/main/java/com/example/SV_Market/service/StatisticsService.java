package com.example.SV_Market.service;

import com.example.SV_Market.repository.UserRepository;
import com.example.SV_Market.repository.ProductRepository;
import com.example.SV_Market.repository.BalanceFluctuationRepository;
import org.springframework.stereotype.Service;

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

    // Get statistics for a specific month
    public Map<String, Object> getMonthlyStatistics(int month, int year) {
        Map<String, Object> statistics = new HashMap<>();

        // New registrations
        int newRegistrations = userRepository.countNewRegistrationsByMonth(month, year);
        statistics.put("newRegistrations", newRegistrations);

        // Product uploads
        int productUploads = productRepository.countProductsUploadedByMonth(month, year);
        statistics.put("productUploads", productUploads);

        // Completed transactions
        Integer completedTransactions = balanceFluctuationRepository.countCompletedTransactionsByMonth(month, year);
        statistics.put("completedTransactions", completedTransactions);

        // Total transaction amount
        Double totalTransactionAmount = balanceFluctuationRepository.sumTransactionAmountByMonth(month, year);
        statistics.put("totalTransactionAmount", (totalTransactionAmount != null) ? totalTransactionAmount : 0.0);

        // Upgrade sales
        Double upgradeSales = balanceFluctuationRepository.sumAmountByUpgradeAndDate("Upgrade", month, year);
        statistics.put("upgradeSales", (upgradeSales != null) ? upgradeSales : 0.0);

        return statistics;
    }

    // Get yearly statistics
    // Get yearly statistics
    public Map<String, Object> getYearlyStatistics(int year) {
        Map<String, Object> yearlyStatistics = new HashMap<>();

        // Arrays to hold monthly data
        double[] totalTransactionAmounts = new double[12];
        double[] upgradeSales = new double[12];
        int[] newUserRegistrations = new int[12]; // For New User Registrations
        int[] completedTransactions = new int[12]; // For Completed Transactions
        int[] productUploads = new int[12]; // For Product Uploads

        // Loop through each month to get statistics
        for (int month = 1; month <= 12; month++) {
            Map<String, Object> monthlyStats = getMonthlyStatistics(month, year);
            totalTransactionAmounts[month - 1] = (double) monthlyStats.get("totalTransactionAmount");
            upgradeSales[month - 1] = (double) monthlyStats.get("upgradeSales");
            newUserRegistrations[month - 1] = (int) monthlyStats.get("newRegistrations");

            // Check if completedTransactions is null before assignment
            completedTransactions[month - 1] = (monthlyStats.get("completedTransactions") != null)
                    ? (int) monthlyStats.get("completedTransactions")
                    : 0; // Default to 0 if null

            productUploads[month - 1] = (int) monthlyStats.get("productUploads");
        }

        yearlyStatistics.put("totalTransactionAmount", totalTransactionAmounts);
        yearlyStatistics.put("upgradeSales", upgradeSales);
        yearlyStatistics.put("newRegistrations", newUserRegistrations);
        yearlyStatistics.put("completedTransactions", completedTransactions);
        yearlyStatistics.put("productUploads", productUploads);

        return yearlyStatistics;
    }

}
