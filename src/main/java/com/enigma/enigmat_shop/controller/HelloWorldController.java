//package com.enigma.enigmat_shop.controller;
//
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//public class HelloWorldController {
//    @GetMapping("/test")
//    public String helloWorld(){
//        return "Hello world";
//    }
//
//    @GetMapping("/hello-world")
//    public List<String> HelloWorld(){
//        List<String> hello = new ArrayList<>();
//        hello.add("Hello");
//        hello.add("World");
//        return hello;
//    }
//
//    @GetMapping("/test2")
//    public Map<String, String> test2(){
//        Map<String, String> test = new HashMap<>();
//        test.put("nama: ", "Fadhil");
//        test.put("umur: ", "17");
//        return test;
//    }
//
//    @GetMapping("/customer/{customerId}")
//    public String test2(@PathVariable String customerId){
//        return "Customer id : " + customerId;
//    }
//
//    @GetMapping("/product")
//    public String getProduct(@RequestParam(name = "name", required = false) String name){
//        return "Customer id : " + name; // /product?name=
//    }
//
//    @PostMapping("/request-body")
//    public String postProduct(@RequestBody HashMap<String, String> body){
//        return "Request body" + body; // /product?name=
//    }
//}
