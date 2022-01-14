package com.enigma.enigmat_shop.service.impl;

import java.util.HashSet;
import java.util.Set;

import com.enigma.enigmat_shop.entity.Role;
import com.enigma.enigmat_shop.entity.User;
import com.enigma.enigmat_shop.entity.UserDetailImpl;
import com.enigma.enigmat_shop.repository.UserRepository;
import com.enigma.enigmat_shop.response.UserResponse;
import com.enigma.enigmat_shop.service.RoleService;
import com.enigma.enigmat_shop.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username doesn't exist"));
        return UserDetailImpl.build(user);
    }

    @Override
    public UserResponse create(User user, Set<Role> roles) {
        user.setRoles(roles);
        User save = userRepository.save(user);

        Set<String> strRoles = new HashSet<>();
        for (Role role : save.getRoles()) {
            strRoles.add(role.getRole().name());
        }

        return new UserResponse(
                save.getId(),
                save.getUsername(),
                save.getCreatedAt(),
                save.getUpdatedAt(),
                strRoles
        );
    }
    
}
