package com.binar.teekmustbe.service.role;

import com.binar.teekmustbe.entitiy.Role;
import com.binar.teekmustbe.enums.Roles;
import com.binar.teekmustbe.repository.RoleRepository;
import com.binar.teekmustbe.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Optional<Role> findByRole(Roles role) {
        return roleRepository.findByRole(role);
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public void save(Role role) {
        roleRepository.save(role);
    }
}
