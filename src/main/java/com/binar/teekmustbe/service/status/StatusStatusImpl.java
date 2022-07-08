package com.binar.teekmustbe.service.status;

import com.binar.teekmustbe.entitiy.Status;
import com.binar.teekmustbe.enums.StatusEnum;
import com.binar.teekmustbe.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusStatusImpl implements StatusService{
    @Autowired
    private StatusRepository statusRepository;

    public Optional<Status> findByStatus(StatusEnum status) {
        return statusRepository.findByStatus(status);
    }

    @Override
    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    public void save(Status status) {
        statusRepository.save(status);
    }
}
