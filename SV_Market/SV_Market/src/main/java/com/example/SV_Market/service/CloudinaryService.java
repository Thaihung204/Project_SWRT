package com.example.SV_Market.service;

import com.cloudinary.Cloudinary;
import com.example.SV_Market.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService {
    private final Cloudinary cloudinary;
    public String upload(MultipartFile file)  {
        try{
            Map data = this.cloudinary.uploader().upload(file.getBytes(), Map.of());
            return data.get("secure_url").toString();
        }catch (IOException io){
            throw new RuntimeException("Image upload fail");
        }
    }

    public void image(MultipartFile[] file) {
        for (int i = 0; i < file.length; i++) {
            String url = upload(file[i]);

        }
    }


}
