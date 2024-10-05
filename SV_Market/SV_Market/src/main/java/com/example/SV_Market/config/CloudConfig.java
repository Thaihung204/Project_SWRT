package com.example.SV_Market.config;
import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudConfig {
    @Bean
    public Cloudinary getCloudinary(){
        Map config = new HashMap();
        config.put("cloud_name", "dnodrsqpj");
        config.put("api_key", "362627719437622");
        config.put("api_secret", "LJ8KjeudEpVtRvTm-QTeYY_xeQ8");
        config.put("secure", true);
        return new Cloudinary(config);
    }
}