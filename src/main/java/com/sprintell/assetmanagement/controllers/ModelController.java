package com.sprintell.assetmanagement.controllers;

import com.sprintell.assetmanagement.models.Model;
import com.sprintell.assetmanagement.services.ModelService;
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
public class ModelController {

    public static final Logger logger = LoggerFactory.getLogger(ModelController.class);

    @Autowired
    ModelService modelService;

    //------Retrieving Models------
    @GetMapping("/models")
    public ResponseEntity<List<Model>> getAllModels(){
        List<Model> models = modelService.getModels();
        if (models.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        logger.info("Returning all Models");
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    @GetMapping("/models/{id}")
    public ResponseEntity<Model> getModel(@PathVariable("id") Long id){
        Optional<Model> model = modelService.getModel(id);

        if (!model.isPresent()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        logger.info("Returning Model with ID: {}", id);
        return new ResponseEntity(model, HttpStatus.OK);
    }

    //----Creating Models
    @PostMapping("/models")
    public ResponseEntity<?> createModel(@RequestBody Model model, UriComponentsBuilder ucBuilder){
        logger.info("Creating Model : {}", model);

        if (modelService.checkExistence(model.getModelId())) {
            logger.error("Unable to create. A Model with name {} already exist", model.getName());
            return new ResponseEntity(new CustomErrorType("Unable to create. A Model with name " +
                    model.getName() + " already exist."),HttpStatus.CONFLICT);
        }

        modelService.addModel(model);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/model/{id}").buildAndExpand(model.getModelId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }

    // ------------------- Update a Model ------------------------------------------------
    @PutMapping("/model/{id}")
    public ResponseEntity<?> updateModel(@PathVariable("id") Long id, @RequestBody Model model) {
        logger.info("Updating Model with id {}", id);

        Boolean update = modelService.updateModel(model, id);

        if (update == false) {
            logger.error("Unable to update. Model with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Model>(modelService.getModel(id).get(),
                HttpStatus.OK);
    }

    // ------------------- Delete a Model-----------------------------------------
    @DeleteMapping("model/{id}")
    public ResponseEntity<?> deleteModel(@PathVariable("id") Long id) {
        logger.info("Fetching & Deleting Model with id {}", id);

        Optional<Model> optModel = modelService.getModel(id);
        if (!optModel.isPresent()) {
            logger.error("Unable to delete. Model with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Model with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        modelService.deleteModel(id);
        return new ResponseEntity<Model>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Models-----------------------------
    @DeleteMapping("/models")
    public ResponseEntity<Model> deleteAllModels() {
        logger.info("Deleting All Models");

        modelService.deleteAllModels();
        return new ResponseEntity<Model>(HttpStatus.NO_CONTENT);
    }
}
