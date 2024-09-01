package com.turkcell.pair3.orderservice.services.concretes;

import com.turkcell.pair3.core.events.CartProductEvent;
import com.turkcell.pair3.core.exception.types.BusinessExceptionFactory;
import com.turkcell.pair3.core.messages.Messages;
import com.turkcell.pair3.core.services.abstracts.MessageService;
import com.turkcell.pair3.orderservice.clients.ProductServiceClient;
import com.turkcell.pair3.orderservice.entities.Order;
import com.turkcell.pair3.orderservice.entities.OrderDetails;
import com.turkcell.pair3.orderservice.entities.factories.OrderDetailsFactory;
import com.turkcell.pair3.orderservice.entities.factories.OrderFactory;
import com.turkcell.pair3.orderservice.repositories.OrderDetailsRepository;
import com.turkcell.pair3.orderservice.repositories.OrderRepository;
import com.turkcell.pair3.orderservice.services.abstracts.OrderService;
import com.turkcell.pair3.orderservice.services.constants.OrderConstants;
import com.turkcell.pair3.orderservice.services.dtos.OrderDetailsListWithPrice;
import com.turkcell.pair3.orderservice.services.dtos.factories.OrderDetailsListWithPriceFactory;
import com.turkcell.pair3.orderservice.services.dtos.requests.CreateOrderRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductServiceClient productServiceClient;
    private final MessageService messageService;
    private final OrderDetailsRepository orderDetailsRepository;

    @Override
    public String createOrder(CreateOrderRequest createOrderRequest) {
        Date now = new Date();
        Order order = OrderFactory.create(now, createOrderRequest.getBillAccountId(),
                createOrderRequest.getBillAddressId(), UUID.randomUUID().toString());

        List<CartProductEvent> cartProducts = productServiceClient.getProductsByCartId(createOrderRequest.getCartId());
        OrderDetailsListWithPrice orderDetailsListWithPrice = createOrderDetailsListAndCalculateTotalPriceFromCartProducts(cartProducts, now);
        order.setTotalPrice(orderDetailsListWithPrice.getPrice());
        saveOrderAndOrderDetailsList(order, orderDetailsListWithPrice.getOrderDetailsList());
        return order.getId();
    }

    @Override
    public void deleteOrder(String orderId) {
        checkIfOrderExistsOrThrowException(orderId);
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Date> getDatesOfOrdersByBillAccounts(List<Integer> billAccountIdList) {
        return null;
    }

    private Date createOrderServiceEndDate(Date now) {
        return new Date(now.getTime() + OrderConstants.orderServiceExpireTimeInMs);
    }

    private OrderDetailsListWithPrice createOrderDetailsListAndCalculateTotalPriceFromCartProducts(List<CartProductEvent> cartProducts, Date now) {
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        AtomicReference<Double> totalPrice = new AtomicReference<>(0.0);
        if (!CollectionUtils.isEmpty(cartProducts)) {
            Date orderServiceEndDate = createOrderServiceEndDate(now);
            cartProducts.stream().forEach(cartProductEvent -> {
                OrderDetails orderDetails = OrderDetailsFactory.create(
                        cartProductEvent.getProductId(), cartProductEvent.getProductSpecId(),
                        now, orderServiceEndDate, productServiceClient.findProductPriceById(cartProductEvent.getProductId())
                );
                totalPrice.updateAndGet(v -> v + orderDetails.getOrderPrice());
                orderDetailsList.add(orderDetails);
            });
        }
        return OrderDetailsListWithPriceFactory.create(orderDetailsList, totalPrice.get());
    }

    @Transactional
    private void saveOrderAndOrderDetailsList(Order order, List<OrderDetails> orderDetailsList) {
        orderRepository.save(order);
        orderDetailsList.stream().forEach(orderDetails -> {
            orderDetails.setOrderId(order.getId());
            orderDetailsRepository.save(orderDetails);
        });
    }

    private void checkIfOrderExistsOrThrowException(String id) {
        if (!orderRepository.existsById(id)) {
            throw BusinessExceptionFactory.createWithMessage(messageService.getMessage(Messages.BusinessErrors.ORDER_NOT_FOUND));
        }
    }
}