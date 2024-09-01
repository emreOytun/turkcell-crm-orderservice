package com.turkcell.pair3.orderservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(value="orders")
public class Order {
    @Id
    private String id;
    private Date orderDate;
    private String orderNumber;
    private Integer billAddressId;
    private Integer billAccountId;
    private Double totalPrice;
    private List<OrderDetails> orderDetails;
}

