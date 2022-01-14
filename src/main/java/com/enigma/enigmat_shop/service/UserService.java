package com.enigma.enigmat_shop.service;

import java.util.Set;

import com.enigma.enigmat_shop.entity.Role;
import com.enigma.enigmat_shop.entity.User;

import com.enigma.enigmat_shop.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
    UserResponse create(User user, Set<Role> roles);
}
