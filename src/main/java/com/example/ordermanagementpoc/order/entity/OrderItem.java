package com.example.ordermanagementpoc.order.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
//@Setter
@ToString
@NoArgsConstructor
public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  @ToString.Exclude
  @Setter
  private Order order;

  @Column(name = "external_item_id")
  private String externalItemId;
  private int quantity;

  public OrderItem(String externalItemId, int quantity) {
    this.externalItemId = externalItemId;
    this.quantity = quantity;
  }
}
