package com.login.demo.service;


import com.login.demo.entity.Role;
import com.login.demo.entity.User;
import com.login.demo.repository.UserEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public UserService(UserEntityRepository userEntityRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userEntityRepository = userEntityRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    /**
     * save user
     *
     * @param userEntity
     * @return
     */
    public User saveUser(User userEntity) {

        userEntity.addRoles(getRolesForNewUser(false));
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        return userEntityRepository.save(userEntity);
    }

    /**
     * Performs a quick check to see what roles the new user could be assigned to.
     *
     * @return list of roles for the new user
     */
    private Set<Role> getRolesForNewUser(Boolean isToBeMadeAdmin) {
        Set<Role> newUserRoles = new HashSet<>(roleService.findAll());
        if (!isToBeMadeAdmin) {
            newUserRoles.removeIf(Role::isAdminRole);
        }
        logger.info("Setting user roles: " + newUserRoles);
        return newUserRoles;
    }
}