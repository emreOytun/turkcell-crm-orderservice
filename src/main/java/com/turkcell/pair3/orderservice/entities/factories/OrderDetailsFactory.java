package com.turkcell.pair3.orderservice.entities.factories;

import com.turkcell.pair3.orderservice.entities.OrderDetails;

import java.util.Date;

public class OrderDetailsFactory {
    private OrderDetailsFactory() {

    }

    public static OrderDetails create(Integer productId, Integer productSpecDetailsId,
                                      Date serviceStartDate, Date serviceEndDate, Double orderPrice) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setProductId(productId);
        orderDetails.setProductSpecDetailsId(productSpecDetailsId);
        orderDetails.setServiceStartDate(serviceStartDate);
        orderDetails.setServiceEndDate(serviceEndDate);
        orderDetails.setOrderPrice(orderPrice);
        return orderDetails;
    }
}
