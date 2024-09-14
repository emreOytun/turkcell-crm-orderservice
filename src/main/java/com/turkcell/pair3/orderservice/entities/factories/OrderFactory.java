package com.turkcell.pair3.orderservice.entities.factories;

import com.turkcell.pair3.orderservice.entities.Order;

import java.util.Date;

public class OrderFactory {
    private OrderFactory() {

    }

    public static Order create(Date orderDate, Integer billAccountId, Integer billAddressId, String orderNumber) {
        Order order = new Order();
        order.setOrderDate(orderDate);
        order.setBillAccountId(billAccountId);
        order.setBillAddressId(billAddressId);
        order.setOrderNumber(orderNumber);
        return order;
    }
}
