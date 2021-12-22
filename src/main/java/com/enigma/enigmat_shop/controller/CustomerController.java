package com.enigma.enigmat_shop.controller;


import com.enigma.enigmat_shop.entity.Customer;
import com.enigma.enigmat_shop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/insert")
    public Customer insert(@RequestBody Customer customer){
        return customerService.save(customer);
    }

    @GetMapping
    public List<Customer> getAll(){
        return customerService.getAll();
    }
    @GetMapping("/{customerId}")
    public Customer getProductById(@PathVariable("customerId") String id){
        return customerService.getById(id);
    }

    @PutMapping("/update")
    public Customer update(@RequestBody Customer customer){
        return customerService.update(customer);
    }

    @DeleteMapping("/delete/{customerId}")
    public String deleteById(@PathVariable("customerId") String id){
        customerService.deleteById(id);
        return "Data dengan id " + id + " berhasil dihapus";
    }

}
