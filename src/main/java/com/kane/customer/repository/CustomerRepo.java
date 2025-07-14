package com.kane.customer.repository;

import com.kane.customer.model.Customer;
import jakarta.validation.constraints.Email;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
  boolean existsByEmail(String email);

  boolean existsByBackupEmail(String backupEmail);

  boolean existsByMobilePhoneNumber(String mobilePhoneNumber);

  boolean existsBySsn(String ssn);

  Optional<Customer> findByEmail(@Email String email);
}
