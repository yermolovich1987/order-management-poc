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

/**
 * Simplified model that shows only order and its items. All other details are skipped.
 */
@Entity
@Table(name = "order")
@Getter
@Setter
@ToString
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @NotNull
  @Enumerated(value = EnumType.STRING)
  private Status status;
  private @Version
  Long version;
  @NaturalId
  private String orderNumber;
  private String comment;
  @OneToMany(mappedBy = "order")
  @ToString.Exclude
  private Set<OrderItem> items;

  private Instant createdDate;
  private String createdBy;
  private Instant updatedDate;
  private String updatedBy;
}