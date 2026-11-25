package com.chengkun.practice.service;


import com.chengkun.practice.dto.CustomerReq;
import com.chengkun.practice.dto.CustomerRes;

import java.util.List;

public interface CustomerService {
    public CustomerRes createCustomer(CustomerReq req);

    public CustomerRes getCustomer(Long id);

    public List<CustomerRes> getAllCustomers();

    public CustomerRes updateCustomer(Long id, CustomerReq req);

    public void deleteCustomer(Long id);
}
