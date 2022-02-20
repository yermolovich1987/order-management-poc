package com.example.ordermanagementpoc.order.web;

import com.example.ordermanagementpoc.order.entity.Order;
import com.example.ordermanagementpoc.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

// TODO Extract logic into the service.
@BasePathAwareController
@RequiredArgsConstructor
public class OrderController {

  private final OrderRepository orderRepository;

  // TODO Check how transactions are working here.
  // TODO Error handling.
  @PostMapping(path = "/orders")
  public HttpEntity<?> placeOrder(
      @RequestBody OrderRequestDto payload,
      Errors errors,
      PersistentEntityResourceAssembler assembler) {
    Order order = payload.toOrder();
    Order persistedOrder = orderRepository.save(order);
    PersistentEntityResource persistentEntityResource = assembler.toFullResource(persistedOrder);

    return ResponseEntity.created(
            persistentEntityResource.getRequiredLink(IanaLinkRelations.SELF).toUri())
        .body(persistentEntityResource);
  }

  // TODO Check how transactions are working here.
  // TODO Error handling.
  @PutMapping(path = "/orders/{orderNumber}")
  public HttpEntity<?> updateOrder(
      @PathVariable String orderNumber,
      @RequestBody OrderRequestDto payload,
      Errors errors,
      PersistentEntityResourceAssembler assembler) {
    Order updateData = payload.toOrder();

    // TODO Throw proper exception.
    Order existedOrder =
        orderRepository
            .findOrderByOrderNumber(orderNumber)
            .orElseThrow(() -> new IllegalStateException("Item not existed"));
    existedOrder.setItems(updateData.getItems());
    existedOrder.setComment(updateData.getComment());
    orderRepository.save(existedOrder);
    PersistentEntityResource persistentEntityResource = assembler.toFullResource(existedOrder);

    return ResponseEntity.ok(persistentEntityResource);
  }

  @DeleteMapping(path = "/orders/{orderNumber}")
  public HttpEntity<?> deleteOrder(@PathVariable String orderNumber) {
    // TODO Throw proper exception.
    Order existedOrder =
        orderRepository
            .findOrderByOrderNumber(orderNumber)
            .orElseThrow(() -> new IllegalStateException("Item not existed"));
    existedOrder.cancel();
    orderRepository.save(existedOrder);

    return ResponseEntity.noContent().build();
  }
}
