package com.enigma.enigmat_shop.service.impl;

import com.enigma.enigmat_shop.dto.ProductDTO;
import com.enigma.enigmat_shop.entity.Product;
import com.enigma.enigmat_shop.exception.NotFoundException;
import com.enigma.enigmat_shop.repository.ProductRepository;
import com.enigma.enigmat_shop.service.ProductService;
import com.enigma.enigmat_shop.specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.save(product); //ngesave ke repository, kenapa dia tau param product karena kita kasih generic jpanya Product
    }

    @Override
    public Product getById(String id) {
        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()){
            return product.get();
        }else{
             throw new NotFoundException(String.format("Product with id %s is not found", id));
        }
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> listPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public void deleteById(String id) {
        Product product = getById(id);
        productRepository.delete(product);
    }

    @Override
    public Product update(Product product) {
        // mencari apakah ada id yang sama

        getById(product.getId());

        // baru deh di update
        return productRepository.save(product);
    }

    @Override
    public Page<Product> listWithPage(Pageable pageable, ProductDTO productDTO) {
        Specification<Product> specification = ProductSpecification.getSpecification(productDTO);
        return productRepository.findAll(specification, pageable);
    }

}
