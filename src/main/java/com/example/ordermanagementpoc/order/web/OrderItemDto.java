package com.example.ordermanagementpoc.order.web;

import com.example.ordermanagementpoc.order.entity.OrderItem;
import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class OrderItemDto {
  @NotNull private String externalItemId;
  private int quantity;

  public OrderItem toOrderItem() {
    return new OrderItem(externalItemId, quantity);
  }
}
