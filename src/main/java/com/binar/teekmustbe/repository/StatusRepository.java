package com.binar.teekmustbe.repository;

import com.binar.teekmustbe.entitiy.Status;
import com.binar.teekmustbe.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> findByStatus(StatusEnum status);
}
