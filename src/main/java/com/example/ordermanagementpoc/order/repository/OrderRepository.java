package com.example.ordermanagementpoc.order.repository;

import com.example.ordermanagementpoc.order.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {}
