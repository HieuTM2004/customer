package com.kane.customer.service.serviceImp;

import com.kane.common.exception.DatabaseConstraintViolationException;
import com.kane.customer.dto.request.CreateCustomerRequest;
import com.kane.customer.mapper.CustomerMapper;
import com.kane.customer.model.Address;
import com.kane.customer.model.Customer;
import com.kane.customer.repository.AddressRepo;
import com.kane.customer.repository.CustomerRepo;
import com.kane.customer.service.CustomerService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImp implements CustomerService {
  private final CustomerRepo customerRepo;
  private final AddressRepo addressRepo;

  @Transactional
  @Override
  public Customer createCustomer(final CreateCustomerRequest createCustomerRequest) {
    try {
      log.info("createCustomerRequest: " + createCustomerRequest);
      Address address = CustomerMapper.INSTANCE.toAddress(createCustomerRequest.getAddress());
      addressRepo.save(address);

      Customer customer = CustomerMapper.INSTANCE.toCustomer(createCustomerRequest);
      customer.setAddress(address);
      customer.setActive(false);
      customerRepo.save(customer);

      return customer;

    } catch (DataIntegrityViolationException e) {
      String rootMsg = e.getRootCause() != null ? e.getRootCause().getMessage() : e.getMessage();

      log.error("PostgreSQL Error: " + rootMsg);
      System.out.println(">>> PostgreSQL Error: " + rootMsg);

      if (rootMsg.contains("address_zip_code_key")) {
        throw new DatabaseConstraintViolationException("Zip Code đã tồn tại");
      } else if (rootMsg.contains("customer_email_key")) {
        throw new DatabaseConstraintViolationException("Email đã tồn tại");
      } else if (rootMsg.contains("email_equals_backup_email")) {
        throw new DatabaseConstraintViolationException("Email và Backup Email phải giống nhau");
      } else {
        throw new DatabaseConstraintViolationException("Lỗi ràng buộc dữ liệu: " + rootMsg);
      }
    }
  }

  @Override
  public Optional<Customer> findByEmail(String username) {
    return customerRepo.findByEmail(username);
  }
}
