package com.sprintell.assetmanagement.services;

import com.sprintell.assetmanagement.models.Brand;
import com.sprintell.assetmanagement.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    private LocalDate timeNw = LocalDate.now();
    private Boolean status = true;

    @Autowired
    BrandRepository brandRepository;

    //Add Brand data
    public Brand addBrand(Brand brand){
        brand.setRegTime(timeNw);
        brand.setStatus(status);

        return brandRepository.saveAndFlush(brand);
    }

    public List <Brand> addBrands(List<Brand> brands){
        for (Brand brand : brands){
            brand.setRegTime(timeNw);
            brand.setStatus(status);
        }

        return brandRepository.saveAll(brands);
    }

    // Get Brand Data

    public Optional<Brand> getBrand(Long id){
        return brandRepository.findById(id);
    }

    /*public List<Brand> getSomeBrands(List<Long> ids) {
        return brandRepository.findByIdIn(ids);
    }*/

    public List<Brand> getBrands(){
        List<Brand> brands = brandRepository.findAll();

        return brands;
    }

    // Search / Delete / Check Existence of Brands Data

    public List<Brand> search(int page, int size, String searchParam){

        List<Brand> brands = brandRepository.findBySearchParameter(searchParam);

        return brands;
    }

    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }


    public void deleteAllBrands(){
        brandRepository.deleteAll();
    }


    public boolean checkExistence(Long id) {

        return brandRepository.existsById(id);
    }

    public long countRecord() {
        long count = brandRepository.count();
        return count;
    }

    public Boolean updateBrand(Brand incomingBrand, Long id) {
        Optional<Brand> optbrand = getBrand(id);

        if (optbrand.isPresent()){
            Brand brand= optbrand.get();
            brand.setName(incomingBrand.getName());
            brand.setDescription(incomingBrand.getDescription());
            brand.setStatus(true);
            brand.setRegTime(LocalDate.now());
            brandRepository.save(brand);
            return true;
        }
        return false;

    }

    public void populateBrands(){
        List<Brand> brands = new ArrayList<>();

        brands.add(new Brand("Dell", "Dell Brands"));
        brands.add(new Brand("Fujitsu", "Futjitsu Brands"));
        brands.add(new Brand("Phillips", "Phillips Brands"));
        brands.add(new Brand("Apple", "Apple Brands"));

        addBrands(brands);
    }

}
