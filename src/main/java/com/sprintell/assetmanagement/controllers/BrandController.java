package com.sprintell.assetmanagement.controllers;

import com.sprintell.assetmanagement.models.Brand;
import com.sprintell.assetmanagement.services.BrandService;
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
public class BrandController {

    public static final Logger logger = LoggerFactory.getLogger(BrandController.class);

    @Autowired
    BrandService brandService;

    //------Retrieving brands------
    @GetMapping("/brands")
    public ResponseEntity<List<Brand>> getAllBrands(){
        List<Brand> brands = brandService.getBrands();
        if (brands.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        logger.info("Returning all brands");
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    @GetMapping("/brands/{id}")
    public ResponseEntity<Brand> getBrand(@PathVariable("id") Long id){
        Optional<Brand> brand = brandService.getBrand(id);

        if (!brand.isPresent()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        logger.info("Returning Brand with ID: {}", id);
        return new ResponseEntity(brand, HttpStatus.OK);
    }

    //----Creating brands
    @PostMapping("/brands")
    public ResponseEntity<?> createBrand(@RequestBody Brand brand, UriComponentsBuilder ucBuilder){
        logger.info("Creating brand : {}", brand);

        if (brandService.checkExistence(brand.getBrandId())) {
            logger.error("Unable to create. A brand with name {} already exist", brand.getName());
            return new ResponseEntity(new CustomErrorType("Unable to create. A brand with name " +
                    brand.getName() + " already exist."),HttpStatus.CONFLICT);
        }

        brandService.addBrand(brand);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/brand/{id}").buildAndExpand(brand.getBrandId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }

    // ------------------- Update a brand ------------------------------------------------
    @PutMapping("/brand/{id}")
    public ResponseEntity<?> updateBrand(@PathVariable("id") Long id, @RequestBody Brand brand) {
        logger.info("Updating brand with id {}", id);

        Boolean update = brandService.updateBrand(brand, id);

        if (update == false) {
            logger.error("Unable to update. brand with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Brand>(brandService.getBrand(id).get(),
                HttpStatus.OK);
    }

    // ------------------- Delete a brand-----------------------------------------
    @DeleteMapping("brand/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable("id") Long id) {
        logger.info("Fetching & Deleting brand with id {}", id);

        Optional<Brand> optbrand = brandService.getBrand(id);
        if (!optbrand.isPresent()) {
            logger.error("Unable to delete. Brand with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. brand with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        brandService.deleteBrand(id);
        return new ResponseEntity<Brand>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All brands-----------------------------
    @DeleteMapping("/brands")
    public ResponseEntity<Brand> deleteAllBrands() {
        logger.info("Deleting All brands");

        brandService.deleteAllBrands();
        return new ResponseEntity<Brand>(HttpStatus.NO_CONTENT);
    }

}
