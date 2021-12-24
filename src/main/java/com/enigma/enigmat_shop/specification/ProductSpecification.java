package com.enigma.enigmat_shop.specification;

import com.enigma.enigmat_shop.dto.ProductDTO;
import com.enigma.enigmat_shop.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {
    public static Specification<Product> getSpecification(ProductDTO productDTO){
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(productDTO.getSearchByProductId() != null){
                    Predicate predicate = criteriaBuilder.like(root.get("id"), "%" + productDTO.getSearchByProductId() + "%");
                    predicates.add(predicate);
                }

                if(productDTO.getSearchByProductName() != null){
                    Predicate predicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("productName")), "%" + productDTO.getSearchByProductName() + "%");
                    predicates.add(predicate);
                }
                Predicate[] predicateArr = predicates.toArray(new Predicate[predicates.size()]);
                return criteriaBuilder.and(predicateArr);
            }
        };
    }
}
