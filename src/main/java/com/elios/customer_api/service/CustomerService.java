package com.elios.customer_api.service;

import com.elios.customer_api.entity.Customer;
import com.elios.customer_api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void createCustomer(Customer customer) {
        customer.setCreated(Instant.now().toEpochMilli());
        customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer updateCustomer(Long id, Customer updateInfo) {
        if (customerRepository.existsById(id)) {
            updateInfo.setUpdated(Instant.now().toEpochMilli());
            updateInfo.setFullName(updateInfo.getFullName());
            updateInfo.setPhone(updateInfo.getPhone());
            return customerRepository.save(updateInfo);
        }
        return null;
    }

    public void deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            Customer referenceById = customerRepository.getReferenceById(id);
            referenceById.setIsActive(false);
            customerRepository.save(referenceById);
        }
    }
}
