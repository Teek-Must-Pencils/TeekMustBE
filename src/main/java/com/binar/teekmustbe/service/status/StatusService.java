package com.binar.teekmustbe.service.status;

import com.binar.teekmustbe.entitiy.Status;
import com.binar.teekmustbe.enums.StatusEnum;

import java.util.List;
import java.util.Optional;

public interface StatusService {
    Optional<Status> findByStatus(StatusEnum status);

    List<Status> findAll();

    void save(Status status);
}
