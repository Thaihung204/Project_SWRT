package com.example.SV_Market.service;


import com.example.SV_Market.entity.ProductImage;
import com.example.SV_Market.entity.Report;
import com.example.SV_Market.entity.ReportImage;
import com.example.SV_Market.repository.ReportImageRepository;
import com.example.SV_Market.request.ProductImageCreationRequest;
import com.example.SV_Market.request.ReportImageCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportImageService {

    @Autowired
    ReportImageRepository reportImageRepository;
    @Autowired
    CloudinaryService cloudinaryService;
    @Autowired
    ReportService reportService;
    public ReportImage createReportImage(ReportImageCreationRequest request){
        String imagePath = cloudinaryService.upload(request.getImage());  // Iterate over each image path from the request
        ReportImage reportImage = new ReportImage();  // Create a new ProductImage object
        reportImage.setPath(imagePath);  // Set the image path
        reportImage.setReport(reportService.getReportById(request.getReportId()));  // Associate the image with the product

        return reportImageRepository.save(reportImage);
    }


    public List<ReportImage> getImages(String reportId) {
        return (List<ReportImage>) reportImageRepository.findById(reportId).orElseThrow(() -> new RuntimeException("ReportImage Not Found!"));
    }
}
