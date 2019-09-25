package com.sprintell.assetmanagement.services;

import com.sprintell.assetmanagement.models.Personnel;
import com.sprintell.assetmanagement.repositories.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PersonnelService {

    private LocalDate timeNw = LocalDate.now();
    private Boolean status = true;

    @Autowired
    PersonnelRepository personnelRepository;

    //Add Personnel data
    public Personnel addPersonnel(Personnel personnel){
        personnel.setRegTime(timeNw);
        personnel.setStatus(status);

        return personnelRepository.saveAndFlush(personnel);
    }

    public List <Personnel> addPersonnels(List<Personnel> personnels){
        for (Personnel personnel : personnels){
            personnel.setRegTime(timeNw);
            personnel.setStatus(status);
        }

        return personnelRepository.saveAll(personnels);
    }

    // Get Personnel Data

    public Optional<Personnel> getPersonnel(Long id){
        return personnelRepository.findById(id);
    }

    public List<Personnel> getSomePersonnels(List<Long> ids) {
        return personnelRepository.findByIdIn(ids);
    }

    public List<Personnel> getPersonnels(int page, int size){
        List<Personnel> personnels = personnelRepository.findAll();

        return personnels;
    }

    // Search / Delete / Check Existence of Personnels Data

    public List<Personnel> search(int page, int size, String searchParam){

        List<Personnel> personnels = personnelRepository.findBySearchParameter(searchParam);

        return personnels;
    }

    public void deletePersonnel(Long id) {
        personnelRepository.deleteById(id);
    }


    public boolean checkExistence(Long id) {

        return personnelRepository.existsById(id);
    }

    public long countRecord() {
        long count = personnelRepository.count();
        return count;
    }

}
