package com.chengkun.practice.service.impl;

import com.chengkun.practice.dto.CustomerReq;
import com.chengkun.practice.dto.CustomerRes;
import com.chengkun.practice.entity.Customer;
import com.chengkun.practice.exception.DuplicatedResourceException;
import com.chengkun.practice.exception.ResourceNotFoundException;
import com.chengkun.practice.mapper.CustomerMapper;
import com.chengkun.practice.repository.CustomerRepository;
import com.chengkun.practice.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor

@Transactional(readOnly = true)
@Service
public class CustomerServiceImpl implements CustomerService {

    public final CustomerRepository customerRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public CustomerRes createCustomer(CustomerReq req) {
        if(customerRepository.existsByEmail(req.email()))
            throw new DuplicatedResourceException("Email " + req.email() + " already exists.");

        Customer customer = CustomerMapper.toEntity(req);
        Customer save = customerRepository.save(customer);

        return CustomerMapper.toResponse(save);
    }

    @Override
    public CustomerRes getCustomer(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Customer of id " + id + " not found.")
        );

        return CustomerMapper.toResponse(customer);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    public CustomerRes updateCustomer(Long id, CustomerReq req) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Customer of id " + id + " not found.")
        );

        customer.setFirstName(req.firstName());
        customer.setLastName(req.lastName());
        customer.setEmail(req.email());

        return CustomerMapper.toResponse(customer);
    }

    @Override
    public List<CustomerRes> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(CustomerMapper::toResponse)
                .toList();
    }

    @Override
    public void deleteCustomer(Long id) {

    }
}
