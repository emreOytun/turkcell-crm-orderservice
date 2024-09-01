package com.turkcell.pair3.orderservice.repositories;

import com.turkcell.pair3.orderservice.entities.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {

    Optional<Order> findById(String orderId);
    //List<Order> findOrdersByBillAccountId(Integer billAccountId);

    //List<String> findOrderIdsByBillAccountId(Integer billAccountId);

}