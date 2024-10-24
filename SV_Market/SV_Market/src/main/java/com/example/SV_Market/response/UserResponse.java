package com.example.SV_Market.response;

import com.example.SV_Market.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class UserResponse {

    private String userId;
    private String userName;
    private String password;
    private String role;
    private String phoneNum;
    private String email;
    private String address;
    private Double balance;
    private String profilePicture;
    private String state;
    private Date createdAt;
    private String senderId;
//    private List<Product> products;
}
