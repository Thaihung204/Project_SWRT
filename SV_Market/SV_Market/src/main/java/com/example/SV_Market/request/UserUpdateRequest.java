package com.example.SV_Market.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
    private String username;
    private String phoneNum;
    private String address;
    private String email;
    private String currentPassword;
    private String newPassword;
    private MultipartFile avatar;
}
