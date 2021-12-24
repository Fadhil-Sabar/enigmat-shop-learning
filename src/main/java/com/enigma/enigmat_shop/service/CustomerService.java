package com.enigma.enigmat_shop.service;

import com.enigma.enigmat_shop.dto.CustomerDTO;
import com.enigma.enigmat_shop.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    Customer save(Customer customer);
    Customer getById(String id);
    List<Customer> getAll();
    void deleteById(String id);
    Customer update(Customer customer);
    Page<Customer> listWithPage(Pageable pageable, CustomerDTO customerDTO);
}
