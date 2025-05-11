package com.micro.managertravel.config;

import com.micro.managertravel.fakedata.DataLoader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoaderRunner implements CommandLineRunner {

    private final DataLoader dataLoader;

    public DataLoaderRunner(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    @Override
    public void run(String... args) throws Exception {
        dataLoader.loadFlightsData();
    }
}
