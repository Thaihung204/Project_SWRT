package com.example.SV_Market.service;


import com.example.SV_Market.entity.*;
import com.example.SV_Market.repository.ProductRepository;
import com.example.SV_Market.repository.ReportRepository;
import com.example.SV_Market.request.FeedbackCreationRequest;
import com.example.SV_Market.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.SV_Market.request.ReportCreationRequest;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportService {
    @Autowired
    ReportRepository reportRepository;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;
    @Autowired
    CloudinaryService cloudinaryService;


    public Report getReportById(String id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }


    public Report createReport(ReportCreationRequest request) {
        Report report = new Report();

        List<ReportImage> reportImages = new ArrayList<>();
        for (String imagePath : cloudinaryService.uploadProductImage(request.getImages())) {
            System.out.println(imagePath);
            ReportImage reportImage = new ReportImage();
            reportImage.setPath(imagePath);
            reportImage.setReport(report);
            reportImages.add(reportImage);
        }

        report.setUser(userService.getUserById(request.getUserId()));
        report.setProduct(productService.getProductById(request.getProductId()));
        report.setImages(reportImages); // Set images to Report entity

        report.setTitle(request.getTitle());
        report.setDescription(request.getDescription());
        report.setState(request.getState());
        report.setResponseMessage(" ");
        report.setOrder(orderService.getOrderById("0"));

        return reportRepository.save(report);
    }



    public  Report createReportOrder (  ReportCreationRequest request) {




        Report report = new Report();

        List<ReportImage> reportImages = new ArrayList<>();  // Create an empty list to store the ProductImage objects

        for (String imagePath : cloudinaryService.uploadProductImage(request.getImages())) {  // Iterate over each image path from the request
            ReportImage reportImage = new ReportImage();  // Create a new ProductImage object
            reportImage.setPath(imagePath);  // Set the image path
            reportImage.setReport(report);  // Associate the image with the product
            reportImages.add(reportImage);  // Add the productImage to the list
        }

        report.setUser(userService.getUserById(request.getUserId()));
        report.setProduct(productService.getProductById("0")); // Fake productId = 0
        report.setImages(reportImages);
        report.setTitle(request.getTitle());
        report.setDescription(request.getDescription());
        report.setState(request.getState());
        report.setResponseMessage(" ");
        report.setOrder(orderService.getOrderById(request.getOrderId()));



        return reportRepository.save(report);


    }


    public List<ReportResponse> viewHistoryProductReportUser(String userId) {
        List<Report> reports = reportRepository.findByUser_UserId(userId);
        return reports.stream()
                .filter(report -> report.getOrder().getOrderId().equals("0"))
                .map(report -> {
                    ReportResponse response = new ReportResponse();
                    response.setTitle(report.getTitle());
                    response.setDescription(report.getDescription());
                    response.setState(report.getState());
                    response.setResponseMessage(report.getResponseMessage());
                    response.setUserName(report.getUser().getUserName());
                    response.setUserId(report.getUser().getUserId());
                    response.setProductName(report.getProduct().getProductName()); // Get product name from product
                    response.setProductId(report.getProduct().getProductId());

                    // Fetch images associated with this specific report
                    List<ReportImageRespone> imageResponses = report.getImages().stream()
                            .map(image -> {
                                ReportImageRespone imageResponse = new ReportImageRespone();
                                imageResponse.setPath(image.getPath());
                                return imageResponse;
                            }).collect(Collectors.toList());
                    response.setImages(imageResponses); // Assuming ReportResponse has a field for images

                    return response;
                }).collect(Collectors.toList());
    }


    public List<ReportResponse> getReporProducttByStateAdmin(String state){
        List<Report> reports = reportRepository.getReportbyState(state);
        return reports.stream()
            .filter(report -> report.getOrder().getOrderId().equals("0")) 
            .map(report -> {
                ReportResponse response = new ReportResponse();
                response.setTitle(report.getTitle());
                response.setDescription(report.getDescription());
                response.setState(report.getState());
                response.setResponseMessage(report.getResponseMessage());
                response.setUserName(report.getUser().getUserName());
                response.setUserId(report.getUser().getUserId());
                response.setProductName(report.getProduct().getProductName());  // Lấy tên sản phẩm từ product
                response.setProductId(report.getProduct().getProductId());
                  response.setReportId(report.getReportId());
                List<ReportImageRespone> imageResponses = report.getImages().stream()
                        .map(image -> {
                            ReportImageRespone imageResponse = new ReportImageRespone();
                            imageResponse.setPath(image.getPath());
                            return imageResponse;
                        }).collect(Collectors.toList());
                response.setImages(imageResponses); // Assuming ReportResponse has a field for images

                return response;



        }).collect(Collectors.toList());
    }



    public List<ReportResponse> viewHistoryOrderReportUser(String userId) {
        List<Report> reports = reportRepository.findByUser_UserId(userId);

        // Sử dụng Stream API để chuyển đổi danh sách Report sang ReportResponse
        return reports.stream().filter(report -> report.getProduct().getProductId().equals("0"))
                .map(report -> {
            ReportResponse response = new ReportResponse();
            response.setTitle(report.getTitle());
            response.setDescription(report.getDescription());
            response.setState(report.getState());
            response.setResponseMessage(report.getResponseMessage());
            response.setUserName(report.getUser().getUserName());
            response.setUserId(report.getUser().getUserId());
            response.setOrderId(report.getOrder().getOrderId());

                    List<ReportImageRespone> imageResponses = report.getImages().stream()
                            .map(image -> {
                                ReportImageRespone imageResponse = new ReportImageRespone();
                                imageResponse.setPath(image.getPath());
                                return imageResponse;
                            }).collect(Collectors.toList());
                    response.setImages(imageResponses);

                    return response;
        }).collect(Collectors.toList());
    }


    public List<ReportResponse> getReportOrderByStateAdmin(String state){
        List<Report> reports = reportRepository.getReportbyState(state);
        return reports.stream().filter(report -> report.getProduct().getProductId().equals("0"))
                .map(report -> {
            ReportResponse response = new ReportResponse();
            response.setReportId(report.getReportId());
            response.setTitle(report.getTitle());
//            response.setProductName(report.getProduct().getProductName());
//            response.setProductId(report.getProduct().getProductId());
            response.setOrderId(report.getOrder().getOrderId());
            response.setUserName(report.getUser().getUserName());
            response.setUserId(report.getUser().getUserId());
            response.setState(report.getState());
            response.setOrder(report.getOrder());
            response.setDescription(report.getDescription());
            response.setResponseMessage(report.getResponseMessage());
                    response.setOrderId(report.getOrder().getOrderId());
                    List<ReportImageRespone> imageResponses = report.getImages().stream()
                            .map(image -> {
                                ReportImageRespone imageResponse = new ReportImageRespone();
                                imageResponse.setPath(image.getPath());
                                return imageResponse;
                            }).collect(Collectors.toList());
                    response.setImages(imageResponses);


            return response;
        }).collect(Collectors.toList());
    }



    public Report answerReport(String reportId, String responseMessage) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("Report not found with id: " + reportId));

        report.setState("Completed");
        report.setResponseMessage(responseMessage);

        return reportRepository.save(report);
    }


}

