package com.enigma.enigmat_shop.controller;

import com.enigma.enigmat_shop.dto.ProductDTO;
import com.enigma.enigmat_shop.entity.Customer;
import com.enigma.enigmat_shop.entity.Product;
import com.enigma.enigmat_shop.service.ProductService;
import com.enigma.enigmat_shop.util.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/insert")
    public ResponseEntity<WebResponse<Product>> createProduct(@RequestBody Product product){
        Product savedProduct = productService.create(product);
        WebResponse<Product> response = new WebResponse<>(String.format("Successfully saved product with id : %s", product.getId()), savedProduct);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<WebResponse<Product>> getProductById(@PathVariable("productId") String id){
        Product product = productService.getById(id);
        WebResponse<Product> response = new WebResponse<>(String.format("Product with id %s found", product.getId()), product);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

//    @GetMapping
//    public List<Product> getProductList(){
//        return productService.getAll();
//    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<WebResponse<String>> deleteProductById(@PathVariable("productId") String id){
        productService.deleteById(id);
        WebResponse<String> response = new WebResponse<>(String.format("Product with id %s has been deleted successfully", id), null);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<WebResponse<Product>> updateProduct(@RequestBody Product product){
        Product updateProduct = productService.update(product);
        WebResponse<Product> response = new WebResponse<>(String.format("Product with id %s has been updated successfully", product.getId()), updateProduct);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

//    @GetMapping
//    public Page<Product> getListWithPage(@RequestParam(name = "size", defaultValue = "10") Integer size,
//                                         @RequestParam(name = "page", defaultValue = "0") Integer page,
//                                         @RequestParam(name = "sortBy", defaultValue = "productName") String sortBy,
//                                         @RequestParam(name = "direction", defaultValue = "ASC") String direction){
//        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
//        Pageable pageable = PageRequest.of(page, size, sort);
//        return productService.listPage(pageable);
//    }

    @GetMapping
    public ResponseEntity<WebResponse<Page<Product>>> findAllWithPage(@RequestParam(name = "size", defaultValue = "10") Integer size,
                                                                        @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                        @RequestParam(name = "sortBy", defaultValue = "productName") String sortBy,
                                                                        @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                                        @RequestParam(name = "productId", required = false) String productId,
                                                                        @RequestParam(name = "productName", required = false)String productName)
    {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        ProductDTO productDTO = new ProductDTO(productName, productId);
        Page<Product> products = productService.listWithPage(pageable, productDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new WebResponse<>(String.format("List of all data, page : %d", page), products));
    }
}
