package com.sprintell.assetmanagement.controllers;


import com.sprintell.assetmanagement.models.Vendor;
import com.sprintell.assetmanagement.services.VendorService;
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
public class VendorController {

    public static final Logger logger = LoggerFactory.getLogger(VendorController.class);


    VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    //------Retrieving Vendors------
    @GetMapping("/vendors")
    public ResponseEntity<List<Vendor>> getAllVendors(){
        List<Vendor> vendors = vendorService.getVendors();
        if (vendors.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        logger.info("Returning all vendors");
        return new ResponseEntity<>(vendors, HttpStatus.OK);
    }

    @GetMapping("/vendor/{id}")
    public ResponseEntity<Vendor> getVendor(@PathVariable("id") Long id){
        Optional<Vendor> vendor = vendorService.getVendor(id);

        if (!vendor.isPresent()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        logger.info("Returning Vendor with ID: {}", id);
        return new ResponseEntity(vendor, HttpStatus.OK);
    }

    @PostMapping("/vendors")
    public ResponseEntity<?> createVendor(@RequestBody Vendor vendor, UriComponentsBuilder ucBuilder){
        logger.info("Creating Vendor : {}", vendor);

        if (vendorService.checkExistence(vendor.getVendorId())) {
            logger.error("Unable to create. A Vendor with number {} already exist", vendor.getVendorNumber());
            return new ResponseEntity(new CustomErrorType("Unable to create. A Vendor with number " +
                    vendor.getVendorNumber() + " already exist."),HttpStatus.CONFLICT);
        }

        vendorService.addVendor(vendor);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/vendor/{id}").buildAndExpand(vendor.getVendorId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }

    @PutMapping("/vendor/{id}")
    public ResponseEntity<?> updateVendor(@PathVariable("id") Long id, @RequestBody Vendor vendor) {
        logger.info("Updating Vendor with id {}", id);

        Boolean update = vendorService.updateVendor(vendor, id);

        if (update == false) {
            logger.error("Unable to update. Vendor with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Vendor>(vendorService.getVendor(id).get(), HttpStatus.OK);
    }

    // ------------------- Delete a Vendor-----------------------------------------
    @DeleteMapping("vendor/{id}")
    public ResponseEntity<?> deleteVendor(@PathVariable("id") Long id) {
        logger.info("Fetching & Deleting Vendor with id {}", id);

        Optional<Vendor> optVendor = vendorService.getVendor(id);
        if (!optVendor.isPresent()) {
            logger.error("Unable to delete. Vendor with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Vendor with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        vendorService.deleteVendor(id);
        return new ResponseEntity<Vendor>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Vendors-----------------------------
    @DeleteMapping("/vendor")
    public ResponseEntity<Vendor> deleteAllVendors() {
        logger.info("Deleting All Vendors");

        vendorService.deleteAllVendors();
        return new ResponseEntity<Vendor>(HttpStatus.NO_CONTENT);
    }



}
