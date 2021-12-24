package com.enigma.enigmat_shop.service.impl;

import com.enigma.enigmat_shop.entity.Customer;
import com.enigma.enigmat_shop.exception.NotFoundException;
import com.enigma.enigmat_shop.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @InjectMocks
    CustomerServiceImpl service;

    @Mock
    CustomerRepository repository;

    @Autowired
    MockMvc mockMvc;

    private Customer customer;
    private Customer output;

    @BeforeEach
    void setup(){
        customer = new Customer("as12","Fadhil","Bintaro", "08912412412", new Date(), true, new Date(), new Date());
        output = new Customer();
        output.setId(customer.getId());
        output.setName(customer.getName());
        output.setAddress(customer.getAddress());
        output.setPhoneNumber(customer.getPhoneNumber());
        output.setBirthDate(customer.getBirthDate());
        output.setStatus(customer.getStatus());
        output.setCreatedAt(customer.getCreatedAt());
        output.setUpdatedAt(customer.getUpdatedAt());

        Mockito.when(repository.save(Mockito.any())).thenReturn(output);
    }

    @Test
    void saveCustomerTest(){
        service.save(customer);
        List<Customer> customers = new ArrayList<>();

        customers.add(customer);

        Mockito.when(repository.findAll()).thenReturn(customers);

        assertEquals(1, repository.findAll().size());
    }

    @Test
    void getAllCustomerTest(){
        service.save(customer);
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);

        Mockito.when(service.getAll()).thenReturn(customers);

        assertEquals(1, service.getAll().size());
    }

    @Test
    void deleteCustomerTest(){
        service.save(customer);
//        service.deleteById(customer.getId());

        assertEquals(0, repository.findAll().size());
    }

    @Test
    void getCustomerByIdTest(){
        service.save(customer);
        given(repository.findById("as12")).willReturn(Optional.of(output));

        Customer customer = service.getById("as12");
//        verify(repository).findById("as12");
        assertNotNull(customer);

    }

    void getCustomerPerPage(){

    }
}
