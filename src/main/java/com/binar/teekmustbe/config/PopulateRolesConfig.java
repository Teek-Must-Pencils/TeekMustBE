package com.binar.teekmustbe.config;

import com.binar.teekmustbe.entitiy.Role;
import com.binar.teekmustbe.enums.Roles;
import com.binar.teekmustbe.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PopulateRolesConfig {
    @Autowired
    private RoleService roleService;

    private static final Logger logger = LoggerFactory.getLogger(PopulateRolesConfig.class);

    @Bean
    public void prerun() {
        for (var role : Roles.values()) {
            var dbRole = roleService.findByRole(role);
                if (!dbRole.isPresent()) {
                logger.info("Role " + role.name() + " is not found, inserting to DB . . .");
                var role1 = new Role();
                role1.setRole(role);
                roleService.save(role1);
                logger.info(roleService.findAll().toString());
            }
        }
    }

}
