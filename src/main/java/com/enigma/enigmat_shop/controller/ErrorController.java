package com.enigma.enigmat_shop.controller;


import com.enigma.enigmat_shop.exception.NotFoundException;
import com.enigma.enigmat_shop.exception.ZeroStockException;
import com.enigma.enigmat_shop.util.WebResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException exception){
        HttpStatus status = HttpStatus.NOT_FOUND;
        WebResponse<String> response = new WebResponse<>(
                exception.getMessage(),
                null
        );
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(value = ZeroStockException.class)
    public ResponseEntity<Object> zeroStockException(ZeroStockException exception){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        WebResponse<String> response = new WebResponse<>(
                exception.getMessage(),
                null
        );
        return new ResponseEntity<>(response, status);
    }
}
