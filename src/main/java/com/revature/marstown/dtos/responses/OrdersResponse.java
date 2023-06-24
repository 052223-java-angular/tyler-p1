package com.revature.marstown.dtos.responses;

import java.math.BigDecimal;
import java.util.Date;

import com.revature.marstown.entities.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrdersResponse {
    private String id;
    private Date createdDate;
    private BigDecimal amount;

    public OrdersResponse(Order order) {
        this.id = order.getId();
        this.createdDate = order.getCreatedDate();
        this.amount = order.getAmount();
    }
}
