package com.turkcell.pair3.orderservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(value="order_details")
public class OrderDetails {
    @Id
    private String id;
    private String orderId;
    private Integer productId;
    private Integer productSpecDetailsId;
    private Integer campaignId;
    private Date serviceStartDate;
    private Date serviceEndDate;
    private Integer quantity;
    private Double orderPrice;
    private Double discount;

}
