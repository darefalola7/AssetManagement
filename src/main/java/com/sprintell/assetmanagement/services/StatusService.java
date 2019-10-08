package com.sprintell.assetmanagement.services;

import com.sprintell.assetmanagement.models.Status;
import com.sprintell.assetmanagement.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StatusService {

    private LocalDate timeNw = LocalDate.now();
    private Boolean mainStatus = true;

    @Autowired
    StatusRepository statusRepository;

    public Boolean updateStatus(Status incomingStatus, Long id) {

        Optional<Status> optstatus = getStatus(id);

        if (optstatus.isPresent()){
            Status status= optstatus.get();
            status.setName(incomingStatus.getName());
            status.setDescription(incomingStatus.getDescription());
            status.setStatus(true);
            status.setRegTime(LocalDate.now());
            statusRepository.save(status);
            return true;
        }
        return false;

    }

    //Add Status data
    public Status addStatus(Status status){
        status.setRegTime(timeNw);
        status.setStatus(mainStatus);

        return statusRepository.saveAndFlush(status);
    }

    public List <Status> addStatuses(List<Status> statuses){
        for (Status status : statuses){
            status.setRegTime(timeNw);
            status.setStatus(mainStatus);
        }

        return statusRepository.saveAll(statuses);
    }

    // Get Status Data

    public Optional<Status> getStatus(Long id){
        return statusRepository.findById(id);
    }

    /*public List<Status> getSomeStatuses(List<Long> ids) {
        return statusRepository.findByIdIn(ids);
    }
*/
    public List<Status> getStatuses(){
        List<Status> statuses = statusRepository.findAll();

        return statuses;
    }

    // Search / Delete / Check Existence of Statuses Data

    public List<Status> search(int page, int size, String searchParam){

        List<Status> statuses = statusRepository.findBySearchParameter(searchParam);

        return statuses;
    }

    public void deleteStatus(Long id) {
        statusRepository.deleteById(id);
    }


    public boolean checkExistence(Long id) {

        return statusRepository.existsById(id);
    }

    public long countRecord() {
        long count = statusRepository.count();
        return count;
    }

    public void deleteAllStatuses() {

        statusRepository.deleteAll();
    }
}
