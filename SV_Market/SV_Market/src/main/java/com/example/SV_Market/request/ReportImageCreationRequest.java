package com.example.SV_Market.request;

import com.example.SV_Market.entity.ReportImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportImageCreationRequest {
    private String reportId;
    protected MultipartFile image;
}
