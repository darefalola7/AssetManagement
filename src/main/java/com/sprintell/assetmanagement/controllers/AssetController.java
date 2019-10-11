package com.sprintell.assetmanagement.controllers;

import com.sprintell.assetmanagement.models.Asset;
import com.sprintell.assetmanagement.services.AssetService;
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
public class AssetController {

    public static final Logger logger = LoggerFactory.getLogger(AssetController.class);

    @Autowired
    AssetService assetService;

    //------Retrieving Assets------
    @GetMapping("/assets")
    public ResponseEntity<List<Asset>> getAllAssets(){
        List<Asset> assets = assetService.getAssets();
        if (assets.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        logger.info("Returning all Assets");
        return new ResponseEntity<>(assets, HttpStatus.OK);
    }

    @GetMapping("/assets/{id}")
    public ResponseEntity<Asset> getAsset(@PathVariable("id") Long id){
        Optional<Asset> asset = assetService.getAsset(id);

        if (!asset.isPresent()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        logger.info("Returning Asset with ID: {}", id);
        return new ResponseEntity(asset, HttpStatus.OK);
    }

    //----Creating Assets
    @PostMapping("/assets")
    public ResponseEntity<?> createAsset(@RequestBody Asset asset, UriComponentsBuilder ucBuilder,
                                         @RequestParam("cat") List<Long> catIds,
                                         @RequestParam("location") Long locationId,
                                         @RequestParam("manufacturer") Long manufacturerId,
                                         @RequestParam("brand") Long brandId,
                                         @RequestParam("model") Long modelId,
                                         @RequestParam("department") Long departmentId,
                                         @RequestParam("vendor") Long vendorId,
                                         @RequestParam("status") Long statusId){
        logger.info("Creating Asset : {}", asset);

        if (assetService.checkExistence(asset.getAssetId())) {
            logger.error("Unable to create. A Asset with name {} already exist", asset.getAssetNumber());
            return new ResponseEntity(new CustomErrorType("Unable to create. A Asset with name " +
                    asset.getAssetNumber() + " already exist."),HttpStatus.CONFLICT);
        }

        assetService.addAsset(asset, catIds, locationId, manufacturerId, brandId,
                modelId, departmentId, vendorId, statusId);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/asset/{id}").buildAndExpand(asset.getAssetId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }

    // ------------------- Update a Asset ------------------------------------------------
    @PutMapping("/asset/{id}")
    public ResponseEntity<?> updateAsset(@PathVariable("id") Long id, @RequestBody Asset asset,@RequestParam("cat") List<Long> catIds,
                                         @RequestParam("location") Long locationId,
                                         @RequestParam("manufacturer") Long manufacturerId,
                                         @RequestParam("brand") Long brandId,
                                         @RequestParam("model") Long modelId,
                                         @RequestParam("department") Long departmentId,
                                         @RequestParam("vendor") Long vendorId,
                                         @RequestParam("status") Long statusId) {
        logger.info("Updating asset with id {}", id);

        Boolean update = assetService.updateAsset(asset, id, catIds, locationId, manufacturerId, brandId,
                modelId, departmentId, vendorId, statusId);

        if (update == false) {
            logger.error("Unable to update. Asset with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Asset>(assetService.getAsset(id).get(),
                HttpStatus.OK);
    }

    // ------------------- Delete a Asset-----------------------------------------
    @DeleteMapping("asset/{id}")
    public ResponseEntity<?> deleteAsset(@PathVariable("id") Long id) {
        logger.info("Fetching & Deleting Asset with id {}", id);

        Optional<Asset> optAsset = assetService.getAsset(id);
        if (!optAsset.isPresent()) {
            logger.error("Unable to delete. Asset with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Asset with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        assetService.deleteAsset(id);
        return new ResponseEntity<Asset>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Assets-----------------------------
    @DeleteMapping("/assets")
    public ResponseEntity<Asset> deleteAllAssets() {
        logger.info("Deleting All Assets");

        assetService.deleteAllAssets();
        return new ResponseEntity<Asset>(HttpStatus.NO_CONTENT);
    }
    
}
