package com.turkcell.pair3.orderservice.services.mappers;

import com.turkcell.pair3.orderservice.entities.Order;
import com.turkcell.pair3.orderservice.services.dtos.requests.CreateOrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order addOrderFromRequest(CreateOrderRequest request);

}