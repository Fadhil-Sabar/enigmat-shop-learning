package com.enigma.enigmat_shop.exception;

public class ZeroStockException extends RuntimeException{
    public ZeroStockException(String message) {
        super(message);
    }
}
