package com.binar.teekmustbe.service;

import com.binar.teekmustbe.entitiy.Role;
import com.binar.teekmustbe.enums.Roles;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<Role> findByRole(Roles role);

    List<Role> findAll();

    void save(Role role1);
}
