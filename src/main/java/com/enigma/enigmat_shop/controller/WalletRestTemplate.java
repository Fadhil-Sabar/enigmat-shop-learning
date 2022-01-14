package com.enigma.enigmat_shop.controller;

import com.enigma.enigmat_shop.dto.WalletDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wallets")
public class WalletRestTemplate {

    @Autowired
    private RestTemplate restTemplate;

    // dto -> json ke object
    @PostMapping
    public ResponseEntity<WalletDTO> createNewWallet(@RequestBody WalletDTO walletDTO){
        String url = "http://localhost:8082/wallets";

        WalletDTO walletDTO1 = new WalletDTO(
                walletDTO.getPhoneNumber(),
                walletDTO.getBalance()
        );

        return restTemplate.postForEntity(
                URI.create(url),
                walletDTO1,
                WalletDTO.class);
    }

//    @GetMapping
//    public List<ResponseEntity<WalletDTO>> getWallet(){
//        String url = "http://localhost:8082/wallets";
//        ResponseEntity<WalletDTO> forEntity = restTemplate.getForEntity(url, WalletDTO.class);
//        List<ResponseEntity<WalletDTO>> test = new ArrayList<>();
//        test.add(forEntity);
//        return test;
//    }
}
