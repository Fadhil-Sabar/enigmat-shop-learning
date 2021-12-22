package com.enigma.enigmat_shop.controller;

import com.enigma.enigmat_shop.entity.Product;
import com.enigma.enigmat_shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/insert")
    public Product createProduct(@RequestBody Product product){
        return productService.create(product);
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable("productId") String id){
        return productService.getById(id);
    }

//    @GetMapping
//    public List<Product> getProductList(){
//        return productService.getAll();
//    }

    @DeleteMapping("/delete/{productId}")
    public void deleteProductById(@PathVariable("productId") String id){
        productService.deleteById(id);
        System.out.println("Data dengan id " + id + " berhasil dihapus");
    }

    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product){
        return productService.update(product);
    }

    @GetMapping
    public Page<Product> getListWithPage(@RequestParam(name = "size", defaultValue = "10") Integer size,
                                         @RequestParam(name = "page", defaultValue = "0") Integer page,
                                         @RequestParam(name = "sortBy", defaultValue = "productName") String sortBy,
                                         @RequestParam(name = "direction", defaultValue = "ASC") String direction){
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return productService.listPage(pageable);
    }
}
