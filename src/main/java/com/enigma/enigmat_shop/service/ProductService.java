package com.enigma.enigmat_shop.service;

import com.enigma.enigmat_shop.entity.Product;

import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product getById(String id);
    List<Product> getAll();
    void deleteById(String id);
    Product update(Product product);
}
