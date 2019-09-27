package com.sprintell.assetmanagement.services;

import com.sprintell.assetmanagement.models.Vendor;
import com.sprintell.assetmanagement.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VendorService {

    private LocalDate timeNw = LocalDate.now();
    private Boolean status = true;

    @Autowired
    VendorRepository vendorRepository;

    //Add Vendor data
    public Vendor addVendor(Vendor vendor){
        vendor.setRegTime(timeNw);
        vendor.setStatus(status);

        return vendorRepository.saveAndFlush(vendor);
    }

    public List <Vendor> addVendors(List<Vendor> vendors){
        for (Vendor vendor : vendors){
            vendor.setRegTime(timeNw);
            vendor.setStatus(status);
        }

        return vendorRepository.saveAll(vendors);
    }

    // Get Vendor Data
    public Optional<Vendor> getVendor(Long id){
        return vendorRepository.findById(id);
    }

    /*public List<Vendor> getSomeVendors(List<Long> ids) {
        return vendorRepository.findByIdIn(ids);
    }*/

    public List<Vendor> getVendors(int page, int size){
        List<Vendor> vendors = vendorRepository.findAll();

        return vendors;
    }

    // Search / Delete / Check Existence of Vendors Data

    public List<Vendor> search(int page, int size, String searchParam){

        List<Vendor> vendors = vendorRepository.findBySearchParameter(searchParam);

        return vendors;
    }

    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }


    public boolean checkExistence(Long id) {

        return vendorRepository.existsById(id);
    }

    public long countRecord() {
        long count = vendorRepository.count();
        return count;
    }

}
