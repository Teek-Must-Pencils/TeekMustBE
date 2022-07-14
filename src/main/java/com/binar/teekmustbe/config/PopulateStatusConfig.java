package com.binar.teekmustbe.config;

import com.binar.teekmustbe.entitiy.Status;
import com.binar.teekmustbe.enums.StatusEnum;
import com.binar.teekmustbe.service.status.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PopulateStatusConfig {
    @Autowired
    private StatusService statusService;

    private static final Logger logger = LoggerFactory.getLogger(PopulateStatusConfig.class);

    @Bean
    public void populateStatus() {
        for (var status : StatusEnum.values()) {
            var dbStatus = statusService.findByStatus(status);
            if (dbStatus.isEmpty()) {
                logger.info("Status " + status.name() + " is not found, inserting to DB . . .");
                var status1 = new Status();
                status1.setStatus(status);
                statusService.save(status1);
                logger.info(statusService.findAll().toString());
            }
        }
    }
}
