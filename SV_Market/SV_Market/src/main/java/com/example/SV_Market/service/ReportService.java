package com.example.SV_Market.service;


import com.example.SV_Market.entity.Report;
import com.example.SV_Market.repository.ReportRepository;
import com.example.SV_Market.request.FeedbackCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.SV_Market.request.ReportCreationRequest;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    ReportRepository reportRepository;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;

    public Report createReport(@RequestBody ReportCreationRequest request) {
        Report report = new Report();

        report.setUser(userService.getUserById(request.getUserId()));
        report.setProduct(productService.getProductById(request.getProductId()));
        report.setTitle(request.getTitle());
        report.setDescription(request.getDescription());
        report.setType(request.getType());
        report.setResponseMessage(request.getResponseMessage());
        return reportRepository.save(report);
    }

    public List viewHistoryReport() {
        return  reportRepository.findAll();
    }

}
