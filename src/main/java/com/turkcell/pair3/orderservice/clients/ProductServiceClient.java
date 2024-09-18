package com.turkcell.pair3.orderservice.clients;

import com.turkcell.pair3.core.configuration.feign.FeignClientConfiguration;
import com.turkcell.pair3.core.events.CartProductEvent;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="productservice", configuration = FeignClientConfiguration.class)
@Retry(name = "feignclient")
public interface ProductServiceClient {

    @GetMapping("/api/products/findProductPriceById/{id}")
    double findProductPriceById(@PathVariable("id") Integer id);

    @GetMapping("/api/carts/{cartId}/getProductsByCartId")
    List<CartProductEvent> getProductsByCartId(@PathVariable("cartId") Integer cartId);

}