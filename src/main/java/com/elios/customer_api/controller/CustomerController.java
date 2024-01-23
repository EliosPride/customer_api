package com.elios.customer_api.controller;

import com.elios.customer_api.dto.CustomerDTO;
import com.elios.customer_api.entity.Customer;
import com.elios.customer_api.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody Customer customer) {
        customerService.createCustomer(customer);
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<CustomerDTO>> readAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        List<CustomerDTO> customerDTOList = customers.stream()
                .map(customer -> {
                    CustomerDTO customerDTO = new CustomerDTO();
                    BeanUtils.copyProperties(customer, customerDTO);
                    return customerDTO;
                })
                .toList();
        return new ResponseEntity<>(customerDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> readCustomer(@PathVariable Long id) {
        Optional<Customer> customerById = customerService.getCustomerById(id);
        return customerById.map(customer -> {
            CustomerDTO customerDTO = new CustomerDTO();
            BeanUtils.copyProperties(customer, customerDTO);
            return new ResponseEntity<>(customerDTO, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody Customer newCustomerInfo) {
        Customer updatedCustomer = customerService.updateCustomer(id, newCustomerInfo);
        if (updatedCustomer != null) {
            CustomerDTO customerDTO = new CustomerDTO();
            BeanUtils.copyProperties(newCustomerInfo, customerDTO);
            return new ResponseEntity<>(customerDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
