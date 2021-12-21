package com.enigma.enigmat_shop.service;

import com.enigma.enigmat_shop.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer save(Customer customer);
    Customer getById(String id);
    List<Customer> getAll();
    void deleteById(String id);
    Customer update(Customer customer);
}
