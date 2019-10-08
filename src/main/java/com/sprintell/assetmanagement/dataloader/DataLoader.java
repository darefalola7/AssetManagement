package com.sprintell.assetmanagement.dataloader;

import com.sprintell.assetmanagement.services.BrandService;
import com.sprintell.assetmanagement.services.LocationService;
import com.sprintell.assetmanagement.services.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    LocationService locationService;
    BrandService brandService;
    PersonnelService personnelService;

    @Autowired
    public DataLoader(LocationService locationService, BrandService brandService, PersonnelService personnelService) {
        this.locationService = locationService;
        this.brandService = brandService;
        this.personnelService = personnelService;
    }

    public DataLoader(LocationService locationService) {
        this.locationService = locationService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadInitialData();
    }

    public void loadInitialData(){
        locationService.populateLocations();
        brandService.populateBrands();
        personnelService.populatePersonnels();
    }


}
