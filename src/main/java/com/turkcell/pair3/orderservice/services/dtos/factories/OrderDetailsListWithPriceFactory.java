package com.turkcell.pair3.orderservice.services.dtos.factories;

import com.turkcell.pair3.orderservice.entities.OrderDetails;
import com.turkcell.pair3.orderservice.services.dtos.OrderDetailsListWithPrice;

import java.util.List;

public class OrderDetailsListWithPriceFactory {
    private OrderDetailsListWithPriceFactory() {

    }

    public static OrderDetailsListWithPrice create(List<OrderDetails> orderDetailsList, Double price) {
        OrderDetailsListWithPrice orderDetailsListWithPrice = new OrderDetailsListWithPrice();
        orderDetailsListWithPrice.setOrderDetailsList(orderDetailsList);
        orderDetailsListWithPrice.setPrice(price);
        return orderDetailsListWithPrice;
    }
}
