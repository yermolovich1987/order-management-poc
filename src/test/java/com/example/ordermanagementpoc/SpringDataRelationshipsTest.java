package com.example.ordermanagementpoc;

import com.example.ordermanagementpoc.order.entity.Order;
import com.example.ordermanagementpoc.order.entity.OrderItem;
import com.example.ordermanagementpoc.order.entity.Status;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

// This test demonstrate how to generate relationship in case of independent entities.
@Disabled
@ExtendWith(SpringExtension.class)
// Starts against the random port to initialize all dependencies, but do actually call the real
// service.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringDataRelationshipsTest {
  public static final String NEW_ORDER_777 = "new_order_777";
  @Autowired private TestRestTemplate template;

  private static String ORDERS_ENDPOINT = "http://localhost:8080/orders";
  private static String ITEMS_ENDPOINT = "http://localhost:8080/orderItems";

  @Test
  public void saveOrderWithItems() {
    template.delete(ORDERS_ENDPOINT + "/" + NEW_ORDER_777);

    OrderItem orderItem = new OrderItem("some_external_id", 777);

    Order order = new Order("Some comment", Set.of(orderItem));
    ResponseEntity<Order> orderResponse =
        template.postForEntity(ORDERS_ENDPOINT, order, Order.class);
    String newOrderLocation = orderResponse.getHeaders().getLocation().toString();

    //    OrderItem orderItem = new OrderItem();
    //    orderItem.setExternalItemId("some_external_id");
    //    orderItem.setQuantity(777);
    //    orderItem.setOrder(order);
    ResponseEntity<OrderItem> orderItemResponse =
        template.postForEntity(ITEMS_ENDPOINT, orderItem, OrderItem.class);
    String newOrderItemLocation = orderItemResponse.getHeaders().getLocation().toString();

    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.add("Content-type", "text/uri-list");
    HttpEntity<String> httpEntity = new HttpEntity<>(newOrderLocation, requestHeaders);
    ResponseEntity<String> linkUpdateResponse =
        template.exchange(
            newOrderItemLocation + "/order", HttpMethod.PUT, httpEntity, String.class);

    ResponseEntity<Order> finalOrderResponse =
        template.getForEntity(newOrderItemLocation + "/order", Order.class);
    assertEquals(NEW_ORDER_777, finalOrderResponse.getBody().getOrderNumber());
  }
}
