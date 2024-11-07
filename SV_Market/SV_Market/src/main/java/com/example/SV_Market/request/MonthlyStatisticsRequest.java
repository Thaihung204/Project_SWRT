package com.example.SV_Market.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class MonthlyStatisticsRequest {
        private int month;
        private int year;
    }

