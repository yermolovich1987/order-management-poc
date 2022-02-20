package com.example.ordermanagementpoc.order.web;

import com.example.ordermanagementpoc.order.entity.Order;
import com.example.ordermanagementpoc.order.entity.OrderItem;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class OrderRequestDto {
  private String comment;
  @NotEmpty private List<OrderItemDto> items;

  public Order toOrder() {
    Set<OrderItem> orderItems =
        items.stream().map(OrderItemDto::toOrderItem).collect(Collectors.toSet());

    return new Order(comment, orderItems);
  }
}
