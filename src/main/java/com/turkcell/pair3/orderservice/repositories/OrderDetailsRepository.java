package com.turkcell.pair3.orderservice.repositories;

import com.turkcell.pair3.orderservice.entities.OrderDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderDetailsRepository extends MongoRepository<OrderDetails, String> {
    //List<String> findOrderDetailsIdsByOrderId(String orderId);

    //Date findOrderDateByOrderDetailsId(String orderDetailsId);

    //Date findServiceEndDateByOrderDetailsId(String orderDetailsId);
}
