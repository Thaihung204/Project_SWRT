package com.example.SV_Market.controller;


import com.example.SV_Market.entity.Report;
import com.example.SV_Market.request.ReportCreationRequest;
import com.example.SV_Market.response.ReportResponse;
import com.example.SV_Market.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    //get report theo user
    @GetMapping("/history")
    public ResponseEntity<List<ReportResponse>> viewHistoryReport(@RequestParam(value = "userId", required = false) String userId) {
        List<ReportResponse> reportHistory = reportService.viewHistoryReport(userId);
        return ResponseEntity.ok(reportHistory); // Trả về danh sách các report cùng với productName
    }

    @GetMapping("/admin/history")
//    public List<ReportResponse> getReportByState() {
//        return reportService.getReportByState("Chưa giải quyết");
//    }
    public ResponseEntity<?> getReportByState() {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.getReportByState("pending"));
    }






}

