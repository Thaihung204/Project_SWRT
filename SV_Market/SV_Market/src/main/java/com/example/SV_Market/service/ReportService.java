package com.example.SV_Market.service;


import com.example.SV_Market.entity.Report;
import com.example.SV_Market.repository.ReportRepository;
import com.example.SV_Market.response.ReportResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.SV_Market.request.ReportCreationRequest;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

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
        report.setState(request.getState());
        report.setResponseMessage(" ");
        return reportRepository.save(report);
    }


    public List<ReportResponse> viewHistoryReport(String userId) {
        List<Report> reports = reportRepository.findByUser_UserId(userId);

        // Sử dụng Stream API để chuyển đổi danh sách Report sang ReportResponse
        return reports.stream().map(report -> {
            ReportResponse response = new ReportResponse();
            response.setTitle(report.getTitle());
            response.setDescription(report.getDescription());
            response.setState(report.getState());
            response.setResponseMessage(report.getResponseMessage());
            response.setUserName(report.getUser().getUserName());
            response.setProductName(report.getProduct().getProductName());  // Lấy tên sản phẩm từ product
            return response;
        }).collect(Collectors.toList());
    }
}

