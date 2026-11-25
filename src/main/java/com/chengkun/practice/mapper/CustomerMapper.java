package com.chengkun.practice.mapper;

import com.chengkun.practice.dto.CustomerReq;
import com.chengkun.practice.dto.CustomerRes;
import com.chengkun.practice.entity.Customer;

public class CustomerMapper {
    public static Customer toEntity(CustomerReq req) {
        return new Customer(
                null,
                req.firstName(),
                req.lastName(),
                req.email()
        );
    }

    public static CustomerRes toResponse(Customer c) {
        return new CustomerRes(
                c.getId(),
                c.getFirstName(),
                c.getLastName(),
                c.getEmail()
        );
    }
}
