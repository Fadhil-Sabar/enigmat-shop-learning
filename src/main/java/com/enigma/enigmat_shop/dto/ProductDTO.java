package com.enigma.enigmat_shop.dto;

public class ProductDTO {
    private String searchByProductId;
    private String searchByProductName;

    public ProductDTO(String searchByProductId, String searchByProductName) {
        this.searchByProductId = searchByProductId;
        this.searchByProductName = searchByProductName;
    }

    public String getSearchByProductId() {
        return searchByProductId;
    }

    public void setSearchByProductId(String searchByProductId) {
        this.searchByProductId = searchByProductId;
    }

    public String getSearchByProductName() {
        return searchByProductName;
    }

    public void setSearchByProductName(String searchByProductName) {
        this.searchByProductName = searchByProductName;
    }
}
