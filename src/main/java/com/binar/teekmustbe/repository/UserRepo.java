package com.binar.teekmustbe.repository;

import com.binar.teekmustbe.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
