package com.example.demo.service;

import com.example.demo.exception.UserRepositoryException;
import com.example.demo.factory.UserRepositoryFactory;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Metrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EntryCounterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntryCounterService.class);
    private UserRepositoryFactory userRepositoryFactory;

    @Autowired
    public EntryCounterService(final UserRepositoryFactory userRepositoryFactory) {
        this.userRepositoryFactory = userRepositoryFactory;
    }

    @Scheduled(fixedRate = 5000)
    public void countNumberOfEntriesInTheDatabase() throws UserRepositoryException {
        final Integer numberOfDbEntries = userRepositoryFactory.getDefaultRepository().countEntries();
        LOGGER.info("There are {} entries in our db", numberOfDbEntries);
        Gauge.builder("DATABASE_ENTRIES_COUNT", numberOfDbEntries, value -> value).strongReference(true).register(Metrics.globalRegistry);
    }
}
