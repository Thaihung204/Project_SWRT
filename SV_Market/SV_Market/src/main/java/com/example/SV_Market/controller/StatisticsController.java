package com.example.SV_Market.controller;

import com.example.SV_Market.request.MonthlyStatisticsRequest;
import com.example.SV_Market.service.StatisticsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    // Endpoint to get statistics for a specific month
    @PostMapping("/admin/statistics/monthly")
    public Map<String, Object> getMonthlyStatistics(@RequestBody MonthlyStatisticsRequest request) {
        return statisticsService.getMonthlyStatistics(request.getMonth(), request.getYear());
    }

    // Endpoint to get statistics for the entire year
    @PostMapping("/admin/statistics/yearly")
    public Map<String, Object> getYearlyStatistics(@RequestBody MonthlyStatisticsRequest request) {
        return statisticsService.getYearlyStatistics(request.getYear());
    }
}
