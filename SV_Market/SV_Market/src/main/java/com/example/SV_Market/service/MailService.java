package com.example.SV_Market.service;


import com.example.SV_Market.dto.DataMailDTO;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

@Service
public interface MailService {
    void sendHtmlMail(DataMailDTO dataMail, String templateName) throws MessagingException;
}
