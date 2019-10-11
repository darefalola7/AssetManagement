package com.sprintell.assetmanagement.services;

import com.sprintell.assetmanagement.models.Category;
import com.sprintell.assetmanagement.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CategoryService {

    private LocalDate timeNw = LocalDate.now();
    private Boolean status = true;

    @Autowired
    private CategoryRepository categoryRepository;

    // Add Categories Data
    public Category addCategory(Category category) {
        category.setRegTime(timeNw);
        category.setStatus(status);

        return categoryRepository.saveAndFlush(category);
    }

    public List<Category> addCategories(List<Category> categories) {

        for (Category category : categories) {
            category.setRegTime(timeNw);
            category.setStatus(status);
        }

        return categoryRepository.saveAll(categories);
    }

    // Get Category Data

    public Optional<Category> getCategory(Long id) {
        return categoryRepository.findById(id);
    }

    /* public List<Category> getSomeCategorys(List<Long> ids) {
        return CategoryRepository.findByIdIn(ids);
    }*/

    public List<Category> getCategories() {

        List<Category> categories = categoryRepository.findAll();

        return categories;
    }


    // Search / Delete / Check Existence of Categories Data

    public List<Category> search(String searchParam) {

        List<Category> categories = categoryRepository.findBySearchParameter(searchParam);

        return categories;
    }


    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public void deleteAllCategories(){
        categoryRepository.deleteAll();
    }


    public boolean checkExistence(Long id) {

        return categoryRepository.existsById(id);
    }

    public long countRecord() {
        long count = categoryRepository.count();
        return count;
    }

    public Boolean updateCategory(Category incomingLoc, Long id){
        Optional<Category> category = getCategory(id);

        if (category.isPresent()){
            Category loc = category.get();
            loc.setName(incomingLoc.getName());
            loc.setDescription(incomingLoc.getDescription());
            loc.setStatus(true);
            loc.setRegTime(LocalDate.now());
            categoryRepository.save(loc);
            return true;
        }
        return false;
    }



}
