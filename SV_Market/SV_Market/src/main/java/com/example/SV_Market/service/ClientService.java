package com.example.SV_Market.service;


import com.example.SV_Market.dto.sdi.ClientSdi;
import org.springframework.stereotype.Service;

@Service
public interface ClientService {
    Boolean create(ClientSdi sdi);
    String layOtp(String email);
}
