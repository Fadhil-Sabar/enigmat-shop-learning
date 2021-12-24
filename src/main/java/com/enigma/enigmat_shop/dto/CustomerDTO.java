package com.enigma.enigmat_shop.dto;

import java.util.Date;

public class CustomerDTO {

    private String searchByCustomerName;
    private String searchByCustomerBirthDate;

    public CustomerDTO(String searchByCustomerName, String searchByCustomerBirthDate) {
        this.searchByCustomerName = searchByCustomerName;
        this.searchByCustomerBirthDate = searchByCustomerBirthDate;
    }

    public String getSearchByCustomerName() {
        return searchByCustomerName;
    }

    public void setSearchByCustomerName(String searchByCustomerName) {
        this.searchByCustomerName = searchByCustomerName;
    }

    public String getSearchByCustomerBirthDate() {
        return searchByCustomerBirthDate;
    }

    public void setSearchByCustomerBirthDate(String searchByCustomerBirthDate) {
        this.searchByCustomerBirthDate = searchByCustomerBirthDate;
    }
}
