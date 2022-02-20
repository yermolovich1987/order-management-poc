package com.example.ordermanagementpoc.order.repository;

import com.example.ordermanagementpoc.order.entity.Order;
import com.example.ordermanagementpoc.order.entity.OrderProjection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(excerptProjection = OrderProjection.class)
public interface OrderRepository extends CrudRepository<Order, Long>, PagingAndSortingRepository<Order, Long> {

  Optional<Order> findOrderByOrderNumber(String orderNumber);
}
