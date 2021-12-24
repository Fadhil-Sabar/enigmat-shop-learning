package com.enigma.enigmat_shop;

import com.enigma.enigmat_shop.controller.CustomerController;
import com.enigma.enigmat_shop.dto.CustomerDTO;
import com.enigma.enigmat_shop.entity.Customer;
import com.enigma.enigmat_shop.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @MockBean
    CustomerService service;

    @Autowired
    CustomerController controller;

    @Autowired
    MockMvc mockMvc;
    //make ini untuk expected


    @Autowired
    ObjectMapper objectMapper;

    private Customer customer;

    @BeforeEach
    void setup() {
        customer = new Customer();
        customer.setId("as12");
        customer.setName("Fadhil");
        customer.setAddress("Jakarta");
        customer.setPhoneNumber("08123456777");
        customer.setBirthDate(new Date());
        customer.setStatus(true);
        customer.setCreatedAt(new Date());
        customer.setUpdatedAt(new Date());
    }

    @Test
    void shouldSave_CustomerSuccess() throws Exception {
        //actual
        when(service.save(any(Customer.class))).thenReturn(customer);


        //Tanpa membuat mapper
        RequestBuilder requestBuilder = post("/customers/insert")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\" : " + "\"as12\"," +
                        "\"customerName\" : " + "\"Fadhil\"," +
                        "\"customerAddress\" : " + "\"Jakarta\"," +
                        "\"customerPhoneNumber\" : " + "\"08123456777\"," +
                        "\"birthDate\" : " + "\"2021-12-23\"," +
                        "\"status\" : " + customer.getStatus() + "," +
                        "\"createdAt\" : " + "\"2021-12-23\"," +
                        "\"updatedAt\" : " + "\"2021-12-23\"" + "}");

        // expected
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()) //membandingkan expected dan actual
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))//cek type contect sama apa beda (json
                .andExpect(jsonPath("$.data.name", Matchers.is(customer.getName()))); //membandingkan expected dan actual datanya

    }

    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Test
    void shouldUpdate_CustomerSuccess() throws Exception{
//        actual
        given(service.update(any(Customer.class))).willAnswer((test) -> test.getArgument(0));
//expect
        mockMvc.perform(put("/customers/update").contentType(MediaType.APPLICATION_JSON).content(asJsonString(customer)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name", Matchers.is(customer.getName())));
    }

//    get
    @Test
    void getCustomerById_Success()throws Exception{
        when(service.getById("as12")).thenReturn(customer);

        RequestBuilder requestBuilder = get("/customers/as12").contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.name", Matchers.is(customer.getName())));
    }
//    delete
    @Test
    void deleteCustomer_shouldSuccess() throws Exception {
//        RequestBuilder requestBuilder = delete("/customers/delete/as12").contentType(MediaType.APPLICATION_JSON);
//
//        mockMvc.perform(requestBuilder)
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.message", Matchers.is("Customer with id as12 has been deleted")));

        String customerId = "a123";
        Customer testcustomer = new Customer(customerId,"Fadhil","Bintaro", "08912412412", new Date(), true, new Date(), new Date());

        //actual ketika menggunakan method void
        doNothing().when(service).deleteById(customerId);
        //expected
        mockMvc.perform(delete("/customers/delete/{id}", customerId))
                .andExpect(status().isOk());
    }

    @Test
    void searchCustomer_perPage() throws Exception {
        Integer page = 0;
        Integer size = 10;
        Pageable pageable = PageRequest.of(page, size);
        CustomerDTO customerDTO = new CustomerDTO(customer.getName(), "2021-12-23");
        Page<Customer> customers = service.listWithPage(pageable, customerDTO);
        when(service.listWithPage(pageable, customerDTO)).thenReturn(customers);


        RequestBuilder requestBuilder = get("/customers").contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.message", Matchers.is("List of all data, page : 0")));

    }
}
