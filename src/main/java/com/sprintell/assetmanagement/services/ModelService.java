package com.sprintell.assetmanagement.services;

import com.sprintell.assetmanagement.models.Model;
import com.sprintell.assetmanagement.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ModelService {

    private LocalDate timeNw = LocalDate.now();
    private Boolean status = true;

    @Autowired
    private ModelRepository modelRepository;

    // Add Models Data
    public Model addModel(Model model) {
        model.setRegTime(timeNw);
        model.setStatus(status);

        return modelRepository.saveAndFlush(model);
    }

    public List<Model> addModels(List<Model> models) {

        for (Model model : models) {
            model.setRegTime(timeNw);
            model.setStatus(status);
        }

        return modelRepository.saveAll(models);
    }

    // Get Models Data

    public Optional<Model> getModel(Long id) {
        return modelRepository.findById(id);
    }

    /* public List<Model> getSomeModels(List<Long> ids) {
        return ModelRepository.findByIdIn(ids);
    }*/

    public List<Model> getModels() {

        List<Model> models = modelRepository.findAll();

        return models;
    }


    // Search / Delete / Check Existence of Models Data

    public List<Model> search(String searchParam) {

        List<Model> models = modelRepository.findBySearchParameter(searchParam);

        return models;
    }


    public void deleteModel(Long id) {
        modelRepository.deleteById(id);
    }

    public void deleteAllModels(){
        modelRepository.deleteAll();
    }


    public boolean checkExistence(Long id) {

        return modelRepository.existsById(id);
    }

    public long countRecord() {
        long count = modelRepository.count();
        return count;
    }

    public Boolean updateModel(Model incomingLoc, Long id){
        Optional<Model> model = getModel(id);

        if (model.isPresent()){
            Model loc = model.get();
            loc.setName(incomingLoc.getName());
            loc.setDescription(incomingLoc.getDescription());
            loc.setStatus(true);
            loc.setRegTime(LocalDate.now());
            modelRepository.save(loc);
            return true;
        }
        return false;
    }


}
