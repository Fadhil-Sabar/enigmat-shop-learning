package com.enigma.enigmat_shop.service.impl;

import com.enigma.enigmat_shop.dto.CustomerDTO;
import com.enigma.enigmat_shop.entity.Customer;
import com.enigma.enigmat_shop.exception.NotFoundException;
import com.enigma.enigmat_shop.repository.CustomerRepository;
import com.enigma.enigmat_shop.service.CustomerService;
import com.enigma.enigmat_shop.specification.CustomerSpesification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getById(String id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()){
            return customer.get();
        }else{
            throw new NotFoundException(String.format("Customer dengan id %s tidak ditemukan", id));
        }
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        Customer customer = getById(id);
        if (customer.getDeleted()) {
            throw new NotFoundException("Customer not found");
        }
        customer.setDeleted(true);

        customerRepository.save(customer);

    }

    @Override
    public Customer update(Customer customer) {
        getById(customer.getId());
        return customerRepository.save(customer);
    }

    @Override
    public Page<Customer> listWithPage(Pageable pageable , CustomerDTO customerDTO) {
        Specification<Customer> specification = CustomerSpesification.getSpecification(customerDTO);
        return customerRepository.findAll(specification, pageable);
    }
}
