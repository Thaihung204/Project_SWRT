package com.example.SV_Market.controller;


import com.example.SV_Market.entity.ReportImage;
import com.example.SV_Market.request.ProductImageCreationRequest;
import com.example.SV_Market.request.ReportImageCreationRequest;
import com.example.SV_Market.service.CloudinaryService;
import com.example.SV_Market.service.ReportImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/report_image")
public class ReportImageController {

    @Autowired
    ReportImageService reportImageService;
    @Autowired
    CloudinaryService cloudinaryService;

    @PostMapping()
    ReportImage createReportImage(@ModelAttribute ReportImageCreationRequest request){
        return reportImageService.createReportImage(request);
    }


}
