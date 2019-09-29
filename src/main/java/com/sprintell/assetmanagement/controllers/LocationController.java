package com.sprintell.assetmanagement.controllers;

import com.sprintell.assetmanagement.models.Location;
import com.sprintell.assetmanagement.services.LocationService;
import com.sprintell.assetmanagement.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LocationController {

    public static final Logger logger = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    LocationService locationService;

    //------Retrieving locations------
    @GetMapping("/locations")
    public ResponseEntity<List<Location>> getAllLocations(){
        List<Location> locations = locationService.getLocations();
        if (locations.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        logger.info("Returning all Locations");
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @GetMapping("/locations/{id}")
    public ResponseEntity<Location> getLocation(@PathVariable("id") Long id){
        Optional<Location> location = locationService.getLocation(id);

        if (!location.isPresent()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        logger.info("Returning Location with ID: {}", id);
        return new ResponseEntity(location, HttpStatus.OK);
    }

    //----Creating locations
    @PostMapping("/locations")
    public ResponseEntity<?> createLocation(@RequestBody Location location, UriComponentsBuilder ucBuilder){
        logger.info("Creating Location : {}", location);

        if (locationService.checkExistence(location.getLocationId())) {
            logger.error("Unable to create. A Location with name {} already exist", location.getName());
            return new ResponseEntity(new CustomErrorType("Unable to create. A Location with name " +
                    location.getName() + " already exist."),HttpStatus.CONFLICT);
        }

        locationService.addLocation(location);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/location/{id}").buildAndExpand(location.getLocationId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }

    // ------------------- Update a Location ------------------------------------------------
    @PutMapping("/location/{id}")
    public ResponseEntity<?> updateLocation(@PathVariable("id") Long id, @RequestBody Location location) {
        logger.info("Updating Location with id {}", id);

        Boolean update = locationService.updateLocation(location, id);

        if (update == false) {
            logger.error("Unable to update. Location with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. Location with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Location>(locationService.getLocation(id).get(),
                HttpStatus.OK);
    }

    // ------------------- Delete a Location-----------------------------------------
    @DeleteMapping("location/{id}")
    public ResponseEntity<?> deleteLocation(@PathVariable("id") Long id) {
        logger.info("Fetching & Deleting Location with id {}", id);

        Optional<Location> loc = locationService.getLocation(id);
        if (!loc.isPresent()) {
            logger.error("Unable to delete. User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Location with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        locationService.deleteLocation(id);
        return new ResponseEntity<Location>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Locations-----------------------------
    @DeleteMapping("/locations")
    public ResponseEntity<Location> deleteAllLocations() {
        logger.info("Deleting All Locations");

        locationService.deleteAllLocations();
        return new ResponseEntity<Location>(HttpStatus.NO_CONTENT);
    }

}
