package com.example.SV_Market.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportCreationRequest {
    private String title;
    private String userId;
    private String productId;
    private MultipartFile[] images;
    private String description;
    private String state;
    private String responseMessage;
    private Image ImageReport;
    private  String orderId;
}
