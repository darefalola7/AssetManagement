package com.sprintell.assetmanagement.controllers;

import com.sprintell.assetmanagement.models.Personnel;
import com.sprintell.assetmanagement.services.PersonnelService;
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
public class PersonnelController {

    public static final Logger logger = LoggerFactory.getLogger(PersonnelController.class);

    @Autowired
    PersonnelService personnelService;

    //------Retrieving Personnels------
    @GetMapping("/personnel")
    public ResponseEntity<List<Personnel>> getAllPersonnels(){
        List<Personnel> personnels = personnelService.getPersonnels();
        if (personnels.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        logger.info("Returning all Personnels");
        return new ResponseEntity<>(personnels, HttpStatus.OK);
    }

    @GetMapping("/personnel/{id}")
    public ResponseEntity<Personnel> getPersonnel(@PathVariable("id") Long id){
        Optional<Personnel> personnel = personnelService.getPersonnel(id);

        if (!personnel.isPresent()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        logger.info("Returning Personnel with ID: {}", id);
        return new ResponseEntity(personnel, HttpStatus.OK);
    }

    @PostMapping("/personnel")
    public ResponseEntity<?> createPersonnel(@RequestBody Personnel personnel, UriComponentsBuilder ucBuilder){
        logger.info("Creating personnel : {}", personnel);

        if (personnelService.checkExistence(personnel.getPersonnelId())) {
            logger.error("Unable to create. A Personnel with number {} already exist", personnel.getPersonnelNumber());
            return new ResponseEntity(new CustomErrorType("Unable to create. A Personnel with number " +
                    personnel.getPersonnelNumber() + " already exist."),HttpStatus.CONFLICT);
        }

        personnelService.addPersonnel(personnel);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/personnel/{id}").buildAndExpand(personnel.getPersonnelId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }

    @PutMapping("/personnel/{id}")
    public ResponseEntity<?> updatePersonnel(@PathVariable("id") Long id, @RequestBody Personnel personnel) {
        logger.info("Updating Personnel with id {}", id);

        Boolean update = personnelService.updatePersonnel(personnel, id);

        if (update == false) {
            logger.error("Unable to update. Personnel with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Personnel>(personnelService.getPersonnel(id).get(), HttpStatus.OK);
    }

    // ------------------- Delete a Personnel-----------------------------------------
    @DeleteMapping("personnel/{id}")
    public ResponseEntity<?> deletePersonnel(@PathVariable("id") Long id) {
        logger.info("Fetching & Deleting personnel with id {}", id);

        Optional<Personnel> optPersonnel = personnelService.getPersonnel(id);
        if (!optPersonnel.isPresent()) {
            logger.error("Unable to delete. Personnel with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Personnel with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        personnelService.deletePersonnel(id);
        return new ResponseEntity<Personnel>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Personnels-----------------------------
    @DeleteMapping("/personnel")
    public ResponseEntity<Personnel> deleteAllPersonnels() {
        logger.info("Deleting All personnels");

        personnelService.deleteAllPersonnels();
        return new ResponseEntity<Personnel>(HttpStatus.NO_CONTENT);
    }


}
