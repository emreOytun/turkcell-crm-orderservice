package com.turkcell.pair3.orderservice.services.abstracts;

import com.turkcell.pair3.orderservice.services.dtos.requests.CreateOrderRequest;

import java.util.Date;
import java.util.List;

public interface OrderService {
    String createOrder(CreateOrderRequest createOrderRequest);

    void deleteOrder(String orderId);

    List<Date> getDatesOfOrdersByBillAccounts(List<Integer> billAccountIdList);
}