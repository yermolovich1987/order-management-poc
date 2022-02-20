package com.example.ordermanagementpoc.order;

import com.example.ordermanagementpoc.order.entity.Order;
import com.example.ordermanagementpoc.order.repository.OrderRepository;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Component
public class SpringDataRestCustomization implements RepositoryRestConfigurer {

  @Override
  public void configureRepositoryRestConfiguration(
      RepositoryRestConfiguration config, CorsRegistry cors) {
    RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);

    config
        .withEntityLookup()
        .forRepository(OrderRepository.class)
        .withIdMapping(Order::getOrderNumber)
        .withLookup(OrderRepository::findOrderByOrderNumber);

    config.getExposureConfiguration()
        .disablePutForCreation()
        .forDomainType(Order.class).withItemExposure((metadata, httpMethods) ->  httpMethods.disable(HttpMethod.PATCH, HttpMethod.DELETE));
  }
}
