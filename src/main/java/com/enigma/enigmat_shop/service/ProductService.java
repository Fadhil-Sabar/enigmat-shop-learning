package com.enigma.enigmat_shop.service;

import com.enigma.enigmat_shop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product getById(String id);
    List<Product> getAll();
    Page<Product> listPage(Pageable pageable);
    void deleteById(String id);
    Product update(Product product);
}
