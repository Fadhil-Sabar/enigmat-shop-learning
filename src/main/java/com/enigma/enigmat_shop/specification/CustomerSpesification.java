package com.enigma.enigmat_shop.specification;

import com.enigma.enigmat_shop.dto.CustomerDTO;
import com.enigma.enigmat_shop.entity.Customer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CustomerSpesification {

    public static Specification<Customer> getSpecification(CustomerDTO customerDTO){
        return new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if(customerDTO.getSearchByCustomerName() != null ){
                    Predicate predicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + customerDTO.getSearchByCustomerName().toLowerCase() + "%");
                    predicates.add(predicate);
                    //root sebagai table mst_customer, get("where name"), like (pattern)
                }

                if (customerDTO.getSearchByCustomerBirthDate() != null){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String modifiedDateFormat = sdf.format(Date.valueOf(customerDTO.getSearchByCustomerBirthDate()));

                    Predicate predicate = criteriaBuilder.equal( // literal tuh karena kita gamungkin blikin yang ada gmt nya
                            criteriaBuilder.function("TO_CHAR", String.class, root.get("birthDate"), criteriaBuilder.literal("yyyy-MM-dd")), modifiedDateFormat);
                    predicates.add(predicate);
                }
                Predicate[] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);
                return criteriaBuilder.and(arrayPredicates);
            }
        };
    }
}
