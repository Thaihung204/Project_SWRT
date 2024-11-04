package com.example.SV_Market.service;


import com.example.SV_Market.entity.ProductImage;
import com.example.SV_Market.entity.Report;
import com.example.SV_Market.entity.ReportImage;
import com.example.SV_Market.repository.ReportImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportImageService {

    @Autowired
    ReportImageRepository reportImageRepository;

    public List<ReportImage> createReportImage(Report report, List<String> images) {
        List<ReportImage> imagesReturn = new ArrayList<>();
        for(String image : images) {
            ReportImage reportImage = new ReportImage();
            reportImage.setReport(report);
            reportImage.setPath(image);
            imagesReturn.add(reportImage);
            reportImageRepository.save(reportImage);
        }
        return imagesReturn;
    }


    public List<ReportImage> getImages(String reportId) {
        return (List<ReportImage>) reportImageRepository.findById(reportId).orElseThrow(() -> new RuntimeException("ReportImage Not Found!"));
    }
}
