package com.sprintell.assetmanagement.controllers;


import com.sprintell.assetmanagement.models.Manufacturer;
import com.sprintell.assetmanagement.services.ManufacturerService;
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
public class ManufacturerController {

    public static final Logger logger = LoggerFactory.getLogger(ManufacturerController.class);

    @Autowired
    ManufacturerService manufacturerService;

    //------Retrieving Manufacturers------
    @GetMapping("/manufacturers")
    public ResponseEntity<List<Manufacturer>> getAllManufacturers(){
        List<Manufacturer> manufacturers = manufacturerService.getManufacturers();
        if (manufacturers.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        logger.info("Returning all Manufacturers");
        return new ResponseEntity<>(manufacturers, HttpStatus.OK);
    }

    @GetMapping("/manufacturer/{id}")
    public ResponseEntity<Manufacturer> getManufacturer(@PathVariable("id") Long id){
        Optional<Manufacturer> manufacturer = manufacturerService.getManufacturer(id);

        if (!manufacturer.isPresent()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        logger.info("Returning Manufacturer with ID: {}", id);
        return new ResponseEntity(manufacturer, HttpStatus.OK);
    }

    //----Creating Manufacturers
    @PostMapping("/manufacturers")
    public ResponseEntity<?> createManufacturer(@RequestBody Manufacturer manufacturer, UriComponentsBuilder ucBuilder){
        logger.info("Creating Manufacturer : {}", manufacturer);

        if (manufacturerService.checkExistence(manufacturer.getManufacturerId())) {
            logger.error("Unable to create. A Manufacturer with name {} already exist", manufacturer.getName());
            return new ResponseEntity(new CustomErrorType("Unable to create. A Manufacturer with name " +
                    manufacturer.getName() + " already exist."),HttpStatus.CONFLICT);
        }

        manufacturerService.addManufacturer(manufacturer);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/Manufacturer/{id}").buildAndExpand(manufacturer.getManufacturerId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }

    // ------------------- Update a Manufacturer ------------------------------------------------
    @PutMapping("/manufacturer/{id}")
    public ResponseEntity<?> updateManufacturer(@PathVariable("id") Long id, @RequestBody Manufacturer manufacturer) {
        logger.info("Updating Manufacturer with id {}", id);

        Boolean update = manufacturerService.updateManufacturer(manufacturer, id);

        if (update == false) {
            logger.error("Unable to update. Manufacturer with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Manufacturer>(manufacturerService.getManufacturer(id).get(),
                HttpStatus.OK);
    }

    // ------------------- Delete a Manufacturer-----------------------------------------
    @DeleteMapping("manufacturer/{id}")
    public ResponseEntity<?> deleteManufacturer(@PathVariable("id") Long id) {
        logger.info("Fetching & Deleting Manufacturer with id {}", id);

        Optional<Manufacturer> optManufacturer = manufacturerService.getManufacturer(id);
        if (!optManufacturer.isPresent()) {
            logger.error("Unable to delete. Manufacturer with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Manufacturer with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        manufacturerService.deleteManufacturer(id);
        return new ResponseEntity<Manufacturer>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Manufacturers-----------------------------
    @DeleteMapping("/manufacturers")
    public ResponseEntity<Manufacturer> deleteAllManufacturers() {
        logger.info("Deleting All Manufacturers");

        manufacturerService.deleteAllManufacturers();
        return new ResponseEntity<Manufacturer>(HttpStatus.NO_CONTENT);
    }
    
}
