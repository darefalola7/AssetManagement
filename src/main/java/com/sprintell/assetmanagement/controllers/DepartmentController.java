package com.sprintell.assetmanagement.controllers;

import com.sprintell.assetmanagement.models.Department;
import com.sprintell.assetmanagement.services.DepartmentService;
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
public class DepartmentController {

    public static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    DepartmentService departmentService;

    //------Retrieving Departments------
    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartments(){
        List<Department> departments = departmentService.getDepartments();
        if (departments.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        logger.info("Returning all Departments");
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable("id") Long id){
        Optional<Department> department = departmentService.getDepartment(id);

        if (!department.isPresent()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        logger.info("Returning Department with ID: {}", id);
        return new ResponseEntity(department.get(), HttpStatus.OK);
    }

    //----Creating Departments
    @PostMapping("/Departments")
    public ResponseEntity<?> createDepartment(@RequestBody Department department, UriComponentsBuilder ucBuilder){
        logger.info("Creating Department : {}", department);

        if (departmentService.checkExistence(department.getDepartmentId())) {
            logger.error("Unable to create. A Department with name {} already exist", department.getName());
            return new ResponseEntity(new CustomErrorType("Unable to create. A Department with name " +
                    department.getName() + " already exist."),HttpStatus.CONFLICT);
        }

        departmentService.addDepartment(department);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/Department/{id}").buildAndExpand(department.getDepartmentId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }

    // ------------------- Update a Department ------------------------------------------------
    @PutMapping("/Department/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable("id") Long id, @RequestBody Department department) {
        logger.info("Updating Department with id {}", id);

        Boolean update = departmentService.updateDepartment(department, id);

        if (update == false) {
            logger.error("Unable to update. Department with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. Department with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Department>(departmentService.getDepartment(id).get(),
                HttpStatus.OK);
    }

    // ------------------- Delete a Department-----------------------------------------
    @DeleteMapping("department/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable("id") Long id) {
        logger.info("Fetching & Deleting department with id {}", id);

        Optional<Department> loc = departmentService.getDepartment(id);
        if (!loc.isPresent()) {
            logger.error("Unable to delete. User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Department with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        departmentService.deleteDepartment(id);
        return new ResponseEntity<Department>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Departments-----------------------------
    @DeleteMapping("/departments")
    public ResponseEntity<Department> deleteAllDepartments() {
        logger.info("Deleting All Departments");

        departmentService.deleteAllDepartments();
        return new ResponseEntity<Department>(HttpStatus.NO_CONTENT);
    }
    
}
