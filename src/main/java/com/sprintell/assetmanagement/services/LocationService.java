package com.sprintell.assetmanagement.services;

import com.sprintell.assetmanagement.models.Location;
import com.sprintell.assetmanagement.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    private LocalDate timeNw = LocalDate.now();
    private Boolean status = true;

    @Autowired
    private LocationRepository locationRepository;

    // Add Locations Data
    public Location addLocation(Location location) {
        location.setRegTime(timeNw);
        location.setStatus(status);

        return locationRepository.saveAndFlush(location);
    }

    public List<Location> addLocations(List<Location> locations) {

        for (Location location : locations) {
            location.setRegTime(timeNw);
            location.setStatus(status);
        }

        return locationRepository.saveAll(locations);
    }

    // Get Locations Data

    public Optional<Location> getLocation(Long id) {
        return locationRepository.findById(id);
    }

    /* public List<Location> getSomeLocations(List<Long> ids) {
        return locationRepository.findByIdIn(ids);
    }*/

    public List<Location> getLocations() {

        List<Location> locations = locationRepository.findAll();

        return locations;
    }


    // Search / Delete / Check Existence of Locations Data

    public List<Location> search(String searchParam) {

        List<Location> locations = locationRepository.findBySearchParameter(searchParam);

        return locations;
    }


    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }

    public void deleteAllLocations(){
        locationRepository.deleteAll();
    }


    public boolean checkExistence(Long id) {

        return locationRepository.existsById(id);
    }

    public long countRecord() {
        long count = locationRepository.count();
        return count;
    }

    public Boolean updateLocation(Location incomingLoc, Long id){
        Optional<Location> location = getLocation(id);

        if (location.isPresent()){
            Location loc = location.get();
            loc.setName(incomingLoc.getName());
            loc.setDescription(incomingLoc.getDescription());
            loc.setStatus(true);
            loc.setRegTime(LocalDate.now());
            locationRepository.save(loc);
            return true;
        }
        return false;
    }

    public void populateLocations(){
        List<Location> locations = new ArrayList<>();
        locations.add(new Location("Assets in Europe", "London"));
        locations.add(new Location("Assets in West Africa", "Lagos"));
        locations.add(new Location("Assets in South Africa", "South Africa"));
        locations.add(new Location("Assets in America", "San francisco"));

        addLocations(locations);
    }

}
