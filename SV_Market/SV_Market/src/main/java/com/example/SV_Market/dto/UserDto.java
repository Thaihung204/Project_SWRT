package com.example.SV_Market.dto;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String userId;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String address;
}
