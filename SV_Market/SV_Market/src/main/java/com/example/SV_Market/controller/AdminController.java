package com.example.SV_Market.controller;


import com.example.SV_Market.request.ReportResponse;
import com.example.SV_Market.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/reportadmins")
public class AdminController {

    private ReportService reportService;

    @GetMapping()
    public ResponseEntity<List<ReportResponse>> viewReports() {
        List<ReportResponse> viewreports = reportService.viewReports();
        return ResponseEntity.ok(viewreports);
    }
}
