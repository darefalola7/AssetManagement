package com.sprintell.assetmanagement.services;

import com.sprintell.assetmanagement.models.Manufacturer;
import com.sprintell.assetmanagement.repositories.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerService {

    private LocalDate timeNw = LocalDate.now();
    private Boolean status = true;

    @Autowired
    ManufacturerRepository manufacturerRepository;

    //Add manufacturer data
    public Manufacturer addManufacturer(Manufacturer manufacturer){
        manufacturer.setRegTime(timeNw);
        manufacturer.setStatus(status);

        return manufacturerRepository.saveAndFlush(manufacturer);
    }

    public List <Manufacturer> addManufacturers(List<Manufacturer> manufacturers){
        for (Manufacturer manufacturer : manufacturers){
            manufacturer.setRegTime(timeNw);
            manufacturer.setStatus(status);
        }

        return manufacturerRepository.saveAll(manufacturers);
    }

    // Get Manufacturer Data

    public Optional<Manufacturer> getManufacturer(Long id){
        return manufacturerRepository.findById(id);
    }

    /*public List<Manufacturer> getSomeManufacturers(List<Long> ids) {
        return manufacturerRepository.findByIdIn(ids);
    }*/

    public List<Manufacturer> getManufacturers(){
        List<Manufacturer> manufacturers = manufacturerRepository.findAll();

        return manufacturers;
    }

    // Search / Delete / Check Existence of Manufacturers Data

    public List<Manufacturer> search(int page, int size, String searchParam){

        List<Manufacturer> manufacturers = manufacturerRepository.findBySearchParameter(searchParam);

        return manufacturers;
    }

    public void deleteManufacturer(Long id) {
        manufacturerRepository.deleteById(id);
    }


    public boolean checkExistence(Long id) {

        return manufacturerRepository.existsById(id);
    }

    public long countRecord() {
        long count = manufacturerRepository.count();
        return count;
    }

    public Boolean updateManufacturer(Manufacturer incoming_manufacturer, Long id) {

        Optional<Manufacturer> optmanuf = getManufacturer(id);

        if (optmanuf.isPresent()){
            Manufacturer manuf= optmanuf.get();
            manuf.setName(incoming_manufacturer.getName());
            manuf.setDescription(incoming_manufacturer.getDescription());
            manuf.setStatus(true);
            manuf.setRegTime(LocalDate.now());
            manufacturerRepository.save(manuf);
            return true;
        }
        return false;


    }

    public void deleteAllManufacturers() {
        manufacturerRepository.deleteAll();
    }
}
