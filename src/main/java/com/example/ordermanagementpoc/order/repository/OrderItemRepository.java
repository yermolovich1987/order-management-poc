package com.example.ordermanagementpoc.order.repository;

import com.example.ordermanagementpoc.order.entity.OrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderItemRepository
    extends CrudRepository<OrderItem, Long>, PagingAndSortingRepository<OrderItem, Long> {}
