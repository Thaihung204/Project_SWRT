package com.example.SV_Market.dto.sdi;

import lombok.Data;

import java.time.LocalDate;
@Data
public class ClientSdi {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
//    private LocalDate dob;
}
