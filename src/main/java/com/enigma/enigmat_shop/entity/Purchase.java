package com.enigma.enigmat_shop.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trx_purchase")
public class Purchase {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne(targetEntity = Customer.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @CreatedDate
    private Date purchaseDate;

    @OneToMany(targetEntity = PurchaseDetail.class, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<PurchaseDetail> purchaseDetails;

    @Column(nullable = false)
    private Integer totalPrice;

    public Purchase(String id, Customer customer, Date purchaseDate, List<PurchaseDetail> purchaseDetails, Integer totalPrice) {
        this.id = id;
        this.customer = customer;
        this.purchaseDate = purchaseDate;
        this.purchaseDetails = purchaseDetails;
        this.totalPrice = totalPrice;
    }

    @PrePersist
    private void createdDate(){
        if (purchaseDate == null) this.purchaseDate = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public List<PurchaseDetail> getPurchaseDetails() {
        return purchaseDetails;
    }

    public void setPurchaseDetails(List<PurchaseDetail> purchaseDetails) {
        this.purchaseDetails = purchaseDetails;
    }

    public Purchase() {
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }
}
