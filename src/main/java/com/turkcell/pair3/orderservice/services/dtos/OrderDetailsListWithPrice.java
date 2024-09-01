package com.turkcell.pair3.orderservice.services.dtos;

import com.turkcell.pair3.orderservice.entities.OrderDetails;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDetailsListWithPrice {
    private List<OrderDetails> orderDetailsList;
    private Double price;
}
