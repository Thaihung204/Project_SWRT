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

    @PostMapping("/products")
    ResponseEntity<String> createReport(@ModelAttribute ReportCreationRequest request) {
        reportService.createReport(request);
        return ResponseEntity.ok("succesfull");
    }
    @PostMapping("/orders")
    ResponseEntity<String>  createReportOrder(@ModelAttribute ReportCreationRequest request) {
          reportService.createReportOrder(request);
        return ResponseEntity.ok("succesfull");
    }

    //get report theo user
    @GetMapping("/product_history")
    public ResponseEntity<List<ReportResponse>> viewHistoryReport(@RequestParam(value = "userId", required = false) String userId) {
        List<ReportResponse> reportHistory = reportService.viewHistoryProductReportUser(userId);
        return ResponseEntity.ok(reportHistory); // Trả về danh sách các report cùng với productName
    }

    @GetMapping("/order_history")
    public ResponseEntity<List<ReportResponse>> viewHistoryOrderReport(@RequestParam(value = "userId", required = false) String userId) {
        List<ReportResponse> reportHistory = reportService.viewHistoryOrderReportUser(userId);
        return ResponseEntity.ok(reportHistory); // Trả về danh sách các report cùng với productName
    }

    @GetMapping("/admin/product_history")
//    public List<ReportResponse> getReportByState() {
//        return reportService.getReportByState("Chưa giải quyết");
//    }
    public ResponseEntity<?> getReportByState() {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.getReporProducttByStateAdmin("pending"));
    }




    @GetMapping("/admin/order_history")
    public ResponseEntity<?> getReportOrderByState() {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.getReportOrderByStateAdmin("pending"));
    }


    @PostMapping("/answerReport")
    public ResponseEntity<Report> answerReport(@RequestParam String reportId, @RequestParam String responseMessage) {
        Report updatedReport = reportService.answerReport(reportId, responseMessage);
        return ResponseEntity.ok(updatedReport);
    }


}

