package com.kane.customer.repository;

import com.kane.customer.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Integer> {
  boolean existsByZipCode(String zipCode);
}
