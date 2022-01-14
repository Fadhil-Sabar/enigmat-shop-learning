package com.enigma.enigmat_shop.controller;


import com.enigma.enigmat_shop.dto.CustomerDTO;
import com.enigma.enigmat_shop.entity.Customer;
import com.enigma.enigmat_shop.service.CustomerService;
import com.enigma.enigmat_shop.util.WebResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;


    @PostMapping("/insert")
    public ResponseEntity<WebResponse<Customer>> insert(@RequestBody Customer customer){
        Customer savedCustomer = customerService.save(customer);
        return  ResponseEntity
                .status(HttpStatus.OK)// | ini tuh constructor
                .body(new WebResponse<>(String.format("Successfully saved data with id %s ", savedCustomer.getId()), savedCustomer));
    }

//    @GetMapping
//    public List<Customer> getAll(){
//        return customerService.getAll();
//    }

    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Authorization token",
                    required = true, dataType = "string", paramType = "header")
    })
    public ResponseEntity<WebResponse<Page<Customer>>>  findAllWithPage(
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "birthDate", required = false)String birthDate
            ){
        Pageable pageable = PageRequest.of(page, size);
        CustomerDTO customerDTO = new CustomerDTO(name, birthDate);
        Page<Customer> customers = customerService.listWithPage(pageable, customerDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new WebResponse<>(String.format("List of all data, page : %d", page), customers));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<WebResponse<Customer>> getProductById(@PathVariable("customerId") String id){

        Customer customer = customerService.getById(id);
        return ResponseEntity
                .status(HttpStatus.OK)// | ini tuh constructor
                .body(new WebResponse<>(String.format("Customer with id %s is found", id), customer));
    }

    @PutMapping("/update")
    public ResponseEntity<WebResponse<Customer>> update(@RequestBody Customer customer){
        Customer updatedCustomer = customerService.update(customer);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new WebResponse<>(String.format("Data with id %s has been updated", customer.getId()), updatedCustomer));
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<WebResponse<String>> deleteById(@PathVariable("customerId") String id){
        customerService.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new WebResponse<>(String.format("Customer with id %s has been deleted", id), null));
    }

}
