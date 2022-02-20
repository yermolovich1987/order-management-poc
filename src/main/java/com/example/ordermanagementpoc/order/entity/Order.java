package com.example.ordermanagementpoc.order.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.time.Instant;
import java.util.Set;

/** Simplified model that shows only order and its items. All other details are skipped. */
@Entity
// Just a dummy name prefix to avoid collision with DB keyword.
@Table(name = "received_order")
@Getter
@Setter
@ToString
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Enumerated(value = EnumType.STRING)
  private Status status;

  @Version private Long version;
  @NaturalId private String orderNumber;
  private String comment;

  @OneToMany(mappedBy = "order")
  @ToString.Exclude
  private Set<OrderItem> items;

  // TODO Try to populate this fields via Security or automatically.
  private Instant createdDate;
  private String createdBy;
  private Instant updatedDate;
  private String updatedBy;
}
