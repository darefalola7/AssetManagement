package com.sprintell.assetmanagement.controllers;

import com.sprintell.assetmanagement.models.Status;
import com.sprintell.assetmanagement.services.StatusService;
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
public class StatusController {

    public static final Logger logger = LoggerFactory.getLogger(StatusController.class);

    @Autowired
    StatusService statusService;

    //------Retrieving Statuses------
    @GetMapping("/status")
    public ResponseEntity<List<Status>> getAllStatuses(){
        List<Status> statuses = statusService.getStatuses();
        if (statuses.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        logger.info("Returning all Statuses");
        return new ResponseEntity<>(statuses, HttpStatus.OK);
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<Status> getStatus(@PathVariable("id") Long id){
        Optional<Status> status = statusService.getStatus(id);

        if (!status.isPresent()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        logger.info("Returning Status with ID: {}", id);
        return new ResponseEntity(status, HttpStatus.OK);
    }

    //----Creating Status
    @PostMapping("/status")
    public ResponseEntity<?> createStatus(@RequestBody Status status, UriComponentsBuilder ucBuilder){
        logger.info("Creating Status : {}", status);

        if (statusService.checkExistence(status.getStatusId())) {
            logger.error("Unable to create. A Status with name {} already exist", status.getName());
            return new ResponseEntity(new CustomErrorType("Unable to create. A Status with name " +
                    status.getName() + " already exist."),HttpStatus.CONFLICT);
        }

        statusService.addStatus(status);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/status/{id}").buildAndExpand(status.getStatusId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }

    // ------------------- Update a Status ------------------------------------------------
    @PutMapping("/status/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable("id") Long id, @RequestBody Status status) {
        logger.info("Updating Status with id {}", id);

        Boolean update = statusService.updateStatus(status, id);

        if (update == false) {
            logger.error("Unable to update. Status with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Status>(statusService.getStatus(id).get(),
                HttpStatus.OK);
    }

    // ------------------- Delete a Status-----------------------------------------
    @DeleteMapping("status/{id}")
    public ResponseEntity<?> deleteStatus(@PathVariable("id") Long id) {
        logger.info("Fetching & Deleting Status with id {}", id);

        Optional<Status> optstatus = statusService.getStatus(id);
        if (!optstatus.isPresent()) {
            logger.error("Unable to delete. Status with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Status with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        statusService.deleteStatus(id);
        return new ResponseEntity<Status>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Statuss-----------------------------
    @DeleteMapping("/status")
    public ResponseEntity<Status> deleteAllStatuses() {
        logger.info("Deleting All statuses");

        statusService.deleteAllStatuses();
        return new ResponseEntity<Status>(HttpStatus.NO_CONTENT);
    }
    
}
