package com.sprintell.assetmanagement.dataloader;

import com.sprintell.assetmanagement.services.LocationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    LocationService locationService;

    public DataLoader(LocationService locationService) {
        this.locationService = locationService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadInitialData();
    }

    public void loadInitialData(){
        locationService.populateLocations();
    }


}
