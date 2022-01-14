package com.enigma.enigmat_shop.repository;

import java.util.Optional;

import com.enigma.enigmat_shop.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);

}
