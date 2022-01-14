package com.enigma.enigmat_shop.repository;

import java.util.Optional;

import com.enigma.enigmat_shop.entity.Role;
import com.enigma.enigmat_shop.entity.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer>{
    Optional<Role> findByRole(UserRole role);
    
    Boolean existsByRole(UserRole role);
}
