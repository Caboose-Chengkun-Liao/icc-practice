package com.chengkun.practice.controller;

import com.chengkun.practice.dto.CustomerReq;
import com.chengkun.practice.dto.CustomerRes;
import com.chengkun.practice.service.CustomerService;
import com.chengkun.practice.service.impl.CustomerServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@AllArgsConstructor
public class CustomerController {
    public final CustomerServiceImpl customerService;

    @PostMapping
    public ResponseEntity<CustomerRes> createCustomer(@RequestBody CustomerReq req) {
        return new ResponseEntity<>(customerService.createCustomer(req), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerRes> getCustomerById(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.getCustomer(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerRes>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CustomerRes> updateCustomer(@RequestParam Long id, CustomerReq req) {
        return ResponseEntity.ok(customerService.updateCustomer(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerRes> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}