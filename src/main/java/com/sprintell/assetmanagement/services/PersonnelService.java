package com.sprintell.assetmanagement.services;

import com.sprintell.assetmanagement.models.Personnel;
import com.sprintell.assetmanagement.repositories.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonnelService {

    private LocalDate timeNw = LocalDate.now();
    private Boolean status = true;
    private static Long personnelCount;


    PersonnelRepository personnelRepository;

    @Autowired
    public PersonnelService(PersonnelRepository personnelRepository) {
        this.personnelRepository = personnelRepository;
        personnelCount = personnelRepository.getMaxId();
    }

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

    /*public List<Personnel> getSomePersonnels(List<Long> ids) {
        return personnelRepository.findByIdIn(ids);
    }*/

    public List<Personnel> getPersonnels(){
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

    public Boolean updatePersonnel(Personnel incomingPersonnel, Long id) {
        Optional<Personnel> optPersonnel = getPersonnel(id);
        if(optPersonnel.isPresent()){
            Personnel personnel = optPersonnel.get();
            personnel.setPersonnelNumber(incomingPersonnel.getPersonnelNumber());
            personnel.setFirstname(incomingPersonnel.getFirstname());
            personnel.setLastname(incomingPersonnel.getLastname());
            personnel.setPhone(incomingPersonnel.getPhone());
            personnel.setEmail(incomingPersonnel.getEmail());
            personnel.setTitle(incomingPersonnel.getTitle());
            personnel.setPersonnelGroup(incomingPersonnel.getPersonnelGroup());
            personnel.setAddress(incomingPersonnel.getAddress());
            personnel.setStatus(true);
            personnel.setRegTime(LocalDate.now());
            personnelRepository.save(personnel);
            return true;
        }

        return false;
    }

    public void deleteAllPersonnels() {
        personnelRepository.deleteAll();

    }

    public void populatePersonnels() {
        List<Personnel> personnels = new ArrayList<>();

        personnels.add(new Personnel(getPersonnelNumber(), "Paul", "Ryan", "999000999",
                "paul@email.com","Engineer","123 Fake Str", "IT"));
        personnels.add(new Personnel(getPersonnelNumber(), "Faith", "Titus", "888000999",
                "faith@email.com","Engineer","126 Fake Str", "IT"));
        personnels.add(new Personnel(getPersonnelNumber(), "Linda", "Russel", "787000999",
                "linda@email.com","HR","129 Fake Str", "Administration"));
        personnels.add(new Personnel(getPersonnelNumber(), "Pat", "Beetle", "454000999",
                "pat@email.com","Secretary","223 Fake Str", "Administration"));

        addPersonnels(personnels);
    }

    public String getPersonnelNumber(){

        personnelCount = (personnelCount == null ? 1l : personnelCount);

        String personnelNumber;
        String formattedStr = String.format("%07d", personnelCount);
        personnelNumber = "PSN" + formattedStr;

        personnelCount = personnelCount + 1l;

        return personnelNumber;
    }

}
