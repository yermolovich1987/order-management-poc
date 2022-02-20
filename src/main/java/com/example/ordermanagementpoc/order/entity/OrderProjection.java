package com.example.ordermanagementpoc.order.entity;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "orderSummary", types = Order.class)
public interface OrderProjection {
  String getOrderNumber();

  Status getStatus();
}
