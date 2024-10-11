package com.example.SV_Market.controller;


import com.example.SV_Market.entity.Report;
import com.example.SV_Market.request.ReportCreationRequest;
import com.example.SV_Market.request.ReportResponse;
import com.example.SV_Market.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @PostMapping()
    Report createReport(@RequestBody ReportCreationRequest request) {

        return reportService.createReport(request);
    }





    @GetMapping("/history")
    public ResponseEntity<List<ReportResponse>> viewHistoryReport(String userId) {
        List<ReportResponse> reportHistory = reportService.viewHistoryReport(userId);
        return ResponseEntity.ok(reportHistory); // Trả về danh sách các report cùng với productName

    }





}

