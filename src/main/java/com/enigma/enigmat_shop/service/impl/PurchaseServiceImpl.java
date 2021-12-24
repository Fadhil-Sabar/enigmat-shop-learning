package com.enigma.enigmat_shop.service.impl;

import com.enigma.enigmat_shop.entity.Product;
import com.enigma.enigmat_shop.entity.Purchase;
import com.enigma.enigmat_shop.entity.PurchaseDetail;
import com.enigma.enigmat_shop.exception.ZeroStockException;
import com.enigma.enigmat_shop.repository.PurchaseRepository;
import com.enigma.enigmat_shop.service.ProductService;
import com.enigma.enigmat_shop.service.PurchaseDetailService;
import com.enigma.enigmat_shop.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    PurchaseDetailService purchaseDetailService;

    @Autowired
    ProductService productService;

    @Override
    public Purchase transaction(Purchase purchase) {
        Purchase save = purchaseRepository.save(purchase); // bikin trx baru

        Integer sum = 0;
        for (PurchaseDetail purchaseDetail : save.getPurchaseDetails()) { // bikin detail
            Product product = productService.getById(purchaseDetail.getProduct().getId()); //ngambil product
            if (product.getStock() > purchaseDetail.getQuantity()){
                product.setStock(product.getStock() - purchaseDetail.getQuantity());
            }else{
                throw new ZeroStockException("Sorry the current stock is 0, please choose other product!");
            }
            productService.update(product);

            purchaseDetail.setPurchase(save);
            purchaseDetail.setProductPrice(product.getProductPrice());
            purchaseDetail.setTotalPrice(purchaseDetail.getQuantity() * purchaseDetail.getProductPrice());
            purchaseDetailService.create(purchaseDetail);

            sum += purchaseDetail.getTotalPrice();
        }
        save.setTotalPrice(sum);
        return save;
    }
}
