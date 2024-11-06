package com.example.SV_Market.response;

import com.example.SV_Market.entity.Order;
import com.example.SV_Market.entity.OrderDetail;
import com.example.SV_Market.entity.Product;
import com.example.SV_Market.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class OrderDetailResponse {

    private ProductResponse product;
    private int quantity;
    private ProductResponse productTrade;
}
