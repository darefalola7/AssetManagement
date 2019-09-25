package com.sprintell.assetmanagement.services;

import com.sprintell.assetmanagement.models.Brand;
import com.sprintell.assetmanagement.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<Brand> getSomeBrands(List<Long> ids) {
        return brandRepository.findByIdIn(ids);
    }

    public List<Brand> getBrands(int page, int size){
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


    public boolean checkExistence(Long id) {

        return brandRepository.existsById(id);
    }

    public long countRecord() {
        long count = brandRepository.count();
        return count;
    }

}
