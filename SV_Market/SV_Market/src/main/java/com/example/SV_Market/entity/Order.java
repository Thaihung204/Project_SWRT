package com.example.SV_Market.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders") // Avoid using reserved keywords like "Order" for table names
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id", length = 50)
    private String orderId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails;

    @Column(name = "type", length = 50, nullable = false)
    private String type; //sell / exchange

    @ManyToOne()
    @JoinColumn(name = "sell_id", nullable = false, referencedColumnName = "userid")
    private User seller;

    @ManyToOne()
    @JoinColumn(name = "buy_id", nullable = false, referencedColumnName = "userid")
    private User buyer;

    @Column(name = "payment_by", nullable = false)
    private String paymentBy;

    private String confirm;
    @Column(name = "state", nullable = false)
    private String state;

    private String total;

    @Column(name = "create_at", nullable = false)
    private LocalDate createAt;


}
