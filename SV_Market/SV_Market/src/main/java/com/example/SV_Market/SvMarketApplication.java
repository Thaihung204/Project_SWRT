package com.example.SV_Market;

import com.example.SV_Market.service.UserService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@SpringBootApplication
public class SvMarketApplication {

	@Autowired
	private JavaMailSender javaMailSender;
	public static void main(String[] args) {
		SpringApplication.run(SvMarketApplication.class, args);
//		UserService userService;
//		userService.getUserById("user1");
	}




}
