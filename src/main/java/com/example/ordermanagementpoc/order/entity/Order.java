package com.example.ordermanagementpoc.order.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;
import org.springframework.util.CollectionUtils;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/** Simplified model that shows only order and its items. All other details are skipped. */
@Entity
// Just a dummy name prefix to avoid collision with DB keyword.
@Table(name = "received_order")
@Getter
// @Setter
@ToString
@NoArgsConstructor
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(updatable = false)
  private Long id;

  @Version private Long version;

  @Column(updatable = false)
  @NaturalId
  private String orderNumber;

  @NotNull
  @Enumerated(value = EnumType.STRING)
  @Setter
  private Status status;

  @Setter private String comment;

  @OneToMany(
      mappedBy = "order",
      cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @ToString.Exclude
  @Setter
  private Set<OrderItem> items = new HashSet<>();

  // TODO Try to populate this fields via Security or automatically.
  private Instant createdDate;
  private String createdBy;
  private Instant updatedDate;
  private String updatedBy;

  public Order(String comment, Set<OrderItem> items) {
    this.orderNumber = UUID.randomUUID().toString();
    this.status = Status.DRAFT;
    this.comment = comment;
    this.setItems(items);
  }

  public void setItems(Set<OrderItem> items) {
    if (!CollectionUtils.isEmpty(items)) {
      this.items = items;
      this.items.forEach(item -> item.setOrder(this));
    }
  }

  public void approve() {
    if (status != Status.DRAFT) {
      throw new IllegalStateException("Wrong state of the order, should be DRAFT.");
    }
    status = Status.APPROVED;
  }

  public void cancel() {
    status = Status.CANCELLED;
  }
}
