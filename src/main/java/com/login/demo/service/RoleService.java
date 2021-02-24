package com.login.demo.service;

import com.login.demo.entity.Role;
import com.login.demo.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Find all roles from the database
     */
    public Collection<Role> findAll() {
        return roleRepository.findAll();
    }
}
