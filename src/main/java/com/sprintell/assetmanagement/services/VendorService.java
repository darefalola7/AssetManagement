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
    private static Long vendorCount;

    VendorRepository vendorRepository;

    @Autowired
    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
        vendorCount = vendorRepository.getMaxId();
    }

    public  void deleteAllVendors() {

        vendorRepository.deleteAll();

    }

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

    public List<Vendor> getVendors(){
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

    public Boolean updateVendor(Vendor incomingVendor, Long id) {
        Optional<Vendor> opt_vendor = getVendor(id);

        if (opt_vendor.isPresent()){
            Vendor vendor = opt_vendor.get();
            vendor.setVendorNumber(incomingVendor.getVendorNumber());
            vendor.setName(incomingVendor.getName());
            vendor.setEmail(incomingVendor.getEmail());
            vendor.setPhone(incomingVendor.getPhone());
            vendor.setMobile(incomingVendor.getMobile());
            vendor.setContactName(incomingVendor.getContactName());
            vendor.setWebsite(incomingVendor.getWebsite());
            vendor.setAddressOne(incomingVendor.getAddressOne());
            vendor.setAddressTwo(incomingVendor.getAddressTwo());
            vendor.setCity(incomingVendor.getCity());
            vendor.setState(incomingVendor.getState());
            vendor.setPostalCode(incomingVendor.getPostalCode());
            vendor.setCountry(incomingVendor.getCountry());
            vendor.setStatus(true);
            vendor.setRegTime(LocalDate.now());

            vendorRepository.save(vendor);
            return true;
        }

        return false;
    }


    

    public String getVendorNumber(){

        vendorCount = (vendorCount == null ? 1l : vendorCount);

        String vendorNumber;
        String formattedStr = String.format("%07d", vendorCount);
        vendorNumber = "VND" + formattedStr;

        vendorCount = vendorCount + 1l;

        return vendorNumber;
    }

}
