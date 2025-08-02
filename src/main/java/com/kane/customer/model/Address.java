package com.kane.customer.model;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Address {
  @Id @GeneratedValue private UUID uuid;

  @Column(nullable = false, name = "address_line1")
  private String addressLine1;

  @Column(nullable = false, name = "address_line2")
  private String addressLine2;

  @Column(nullable = false, unique = true, name = "zip_code")
  private String zipCode;

  @Column(nullable = false)
  private String city;

  @OneToOne(mappedBy = "address")
  private Customer customer;
}
