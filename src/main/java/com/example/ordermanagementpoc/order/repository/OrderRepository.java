package com.example.ordermanagementpoc.order.repository;

import com.example.ordermanagementpoc.order.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Long> {

  Optional<Order> findOrderByOrderNumber(String orderNumber);
}
